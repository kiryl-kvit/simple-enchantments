package com.kvit.client;

import com.kvit.SimpleEnchantments;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SimpleEnchantmentsClient implements ClientModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEnchantments.MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("{} client initialized", SimpleEnchantments.MOD_ID);
	}
}
