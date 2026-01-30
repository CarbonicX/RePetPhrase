package com.carbo.repetphrase;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carbo.repetphrase.config.ConfigManager;

public class RePetPhrase implements ModInitializer {
	public static final String MOD_ID = "repetphrase";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigManager.load();
	}
}