package com.carbo.repetphrase.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigData {
    public boolean enable = false;
    public String ignoreMark = "#";
    public List<String> prefixs = new ArrayList<>();
    public List<String> suffixs = new ArrayList<>();
    public List<String> sentencePrefixs = new ArrayList<>();
    public List<String> sentenceSuffixs = new ArrayList<>();
    public Map<String, List<String>> replaces = new HashMap<>();
    public List<String> replaceKeys = new ArrayList<>();
    public Map<String, List<String>> petphrases = new HashMap<>();
}
