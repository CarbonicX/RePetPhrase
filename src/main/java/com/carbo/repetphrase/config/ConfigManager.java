package com.carbo.repetphrase.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.carbo.repetphrase.RePetPhrase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = 
        FabricLoader.getInstance().getConfigDir().resolve("repetphrase.json").toFile();
    public static ModConfig MOD_CONFIG = new ModConfig();
    public static ConfigData CONFIG = new ConfigData();

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            MOD_CONFIG = GSON.fromJson(reader, ModConfig.class);
        } catch (Exception e) {
            RePetPhrase.LOGGER.error(e.getMessage());
        }
        
        processConfig();
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(MOD_CONFIG, writer);
        } catch (Exception e) {
            RePetPhrase.LOGGER.error(e.getMessage());
        }
        processConfig();
    }
    
    private static void processConfig() {
        CONFIG.enable = MOD_CONFIG.enable;
        CONFIG.ignoreMark = MOD_CONFIG.ignoreMark;
        CONFIG.prefixs = split(MOD_CONFIG.prefix);
        CONFIG.suffixs = split(MOD_CONFIG.suffix);
        CONFIG.sentencePrefixs = split(MOD_CONFIG.sentencePrefix);
        CONFIG.sentenceSuffixs = split(MOD_CONFIG.sentenceSuffix);
        for (String s : MOD_CONFIG.replaces) {
            List<String> strings = split(s);
            if (strings.size() > 1) {
                CONFIG.replaces.put(strings.get(0), strings.subList(1, strings.size()));
            }
        }
        List<String> keys = new ArrayList<>(CONFIG.replaces.keySet());
        keys.sort((a, b) -> b.length() - a.length());
        CONFIG.replaceKeys = keys;
        for (String s : MOD_CONFIG.petphrases) {
            List<String> strings = split(s);
            if (strings.size() > 1) {
                CONFIG.petphrases.put(strings.get(0), strings.subList(1, strings.size()));
            }
        }
    }
    
    private static List<String> split(String text) {
        List<String> strings = new ArrayList<>();
        if (text.isEmpty()) return strings;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != ';') {
                sb.append(c);
            } else if (i != text.length() - 1) {
                if (text.charAt(i + 1) == ';') {
                    sb.append(";");
                    i++;
                } else {
                    strings.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        strings.add(sb.toString());
        return strings;
    }
}
