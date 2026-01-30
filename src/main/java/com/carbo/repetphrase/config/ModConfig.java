package com.carbo.repetphrase.config;

import java.util.ArrayList;
import java.util.List;

public class ModConfig {
    public boolean enable = false;
    public String ignoreMark = "#";
    public String prefix = "";
    public String suffix = "";
    public String sentencePrefix = "";
    public String sentenceSuffix = "";
    public List<String> replaces = new ArrayList<>();
    public List<String> petphrases = new ArrayList<>();
}