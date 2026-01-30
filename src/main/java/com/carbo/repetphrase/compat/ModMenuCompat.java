package com.carbo.repetphrase.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import com.carbo.repetphrase.config.ConfigScreen;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ConfigScreen.generateBuilder(parent);
    }
}
