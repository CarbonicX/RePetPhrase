package com.carbo.repetphrase.config;

import java.util.ArrayList;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {
    public static Screen generateBuilder(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("RePetPhrase 配置"));
        ConfigCategory general = builder.getOrCreateCategory(Text.literal("常规"));
        
        general.addEntry(
                builder.entryBuilder()
                .startBooleanToggle(
                        Text.literal("启用"),
                        ConfigManager.MOD_CONFIG.enable
                )
                .setTooltip(
                        Text.literal("启用和关闭所有功能")
                )
                .setDefaultValue(false)
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.enable = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrField(
                        Text.literal("忽略标记"), 
                        ConfigManager.MOD_CONFIG.ignoreMark
                )
                .setTooltip(
                        Text.literal("以该标记开头的消息将不会被修改")
                )
                .setDefaultValue("#")
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.ignoreMark = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrField(
                        Text.literal("消息前缀"), 
                        ConfigManager.MOD_CONFIG.prefix
                )
                .setTooltip(
                        Text.literal("添加在消息开头的前缀"),
                        Text.literal("多个随机前缀之间用英文分号隔开"),
                        Text.literal("文本内使用分号需用两个分号"),
                        Text.literal(" - 前缀1;前缀2;...")
                )
                .setDefaultValue("")
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.prefix = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrField(
                        Text.literal("消息后缀"), 
                        ConfigManager.MOD_CONFIG.suffix
                )
                .setTooltip(
                        Text.literal("添加在消息结尾的后缀"),
                        Text.literal("多个随机后缀之间用英文分号隔开"),
                        Text.literal("文本内使用分号需用两个分号"),
                        Text.literal(" - 后缀1;后缀2;...")
                )
                .setDefaultValue("")
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.suffix = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrField(
                        Text.literal("短句前缀"), 
                        ConfigManager.MOD_CONFIG.sentencePrefix
                )
                .setTooltip(
                        Text.literal("添加在以空格分隔的短句开头的前缀"),
                        Text.literal("多个随机前缀之间用英文分号隔开"),
                        Text.literal("文本内使用分号需用两个分号"),
                        Text.literal(" - 前缀1;前缀2;...")
                )
                .setDefaultValue("")
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.sentencePrefix = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrField(
                        Text.literal("短句后缀"), 
                        ConfigManager.MOD_CONFIG.sentenceSuffix
                )
                .setTooltip(
                        Text.literal("添加在以空格分隔的短句结尾的后缀"),
                        Text.literal("多个随机后缀之间用英文分号隔开"),
                        Text.literal("文本内使用分号需用两个分号"),
                        Text.literal(" - 后缀1;后缀2;...")
                )
                .setDefaultValue("")
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.sentenceSuffix = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrList(
                        Text.literal("替换"), 
                        ConfigManager.MOD_CONFIG.replaces
                )
                .setTooltip(
                        Text.literal("替换指定文本"),
                        Text.literal("文本内使用分号需用两个分号"),
                        Text.literal(" - 待替换文本;新文本1;新文本2;...")
                )
                .setDefaultValue(new ArrayList<>())
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.replaces = value
                )
                .build()
        );
        general.addEntry(
                builder.entryBuilder()
                .startStrList(
                        Text.literal("惯用语"), 
                        ConfigManager.MOD_CONFIG.petphrases
                )
                .setTooltip(
                        Text.literal("将与文本完全符合的消息直接替换为惯用语"),
                        Text.literal("文本内使用分号需用两个分号"),
                        Text.literal(" - 消息;惯用语1;惯用语2;...")
                )
                .setDefaultValue(new ArrayList<>())
                .setSaveConsumer(value -> 
                        ConfigManager.MOD_CONFIG.petphrases = value
                )
                .build()
        );
        
        builder.setSavingRunnable(ConfigManager::save);
        return builder.build();
    }
}
