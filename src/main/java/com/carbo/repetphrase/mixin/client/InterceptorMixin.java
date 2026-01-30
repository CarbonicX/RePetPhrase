package com.carbo.repetphrase.mixin.client;

import com.carbo.repetphrase.RePetPhrase;
import com.carbo.repetphrase.config.ConfigData;
import com.carbo.repetphrase.config.ConfigManager;
import java.util.List;
import java.util.Random;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public class InterceptorMixin {
    private static Random random = new Random();
    
    @ModifyVariable(
            method = "sendChatMessage",
            at = @At("HEAD"),
            argsOnly = true
    )
    private String modifyMessage(String message) {
        return processMessage(message);
    }
    
    private static String processMessage(String message) {
        ConfigData config = ConfigManager.CONFIG;
        if (!config.enable) return message;
        if (message.startsWith(config.ignoreMark)) return message.substring(config.ignoreMark.length());
        if (config.petphrases.keySet().contains(message)) return randomChoose(config.petphrases.get(message));
        
        StringBuilder sb = new StringBuilder();
        boolean isBlank = false;
        for (int i = 0; i < message.length(); ) {
            boolean matched = false;
            String suffix = null;
            if (message.charAt(i) == ' ' && !isBlank) {
                suffix = randomChoose(config.sentenceSuffixs);
                isBlank = true;
            } else if (isBlank && message.charAt(i) != ' ') {
                sb.append(randomChoose(config.sentencePrefixs));
                isBlank = false;
            }
            for (String k : config.replaceKeys) {
                if (message.startsWith(k, i)) {
                    sb.append(randomChoose(config.replaces.get(k)));
                    i += k.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                // 替换的优先级大于短句后缀
                if (suffix != null) sb.append(suffix);
                sb.append(message.charAt(i));
                i++;
            }
        }
        
        return randomChoose(config.prefixs) + sb.toString() + randomChoose(config.suffixs);
    }
    
    private static String randomChoose(List<String> strings) {
        if (strings.isEmpty()) return "";
        if (strings.size() == 1) return strings.get(0);
        return strings.get(random.nextInt(strings.size()));
    }
}
