package com.kingfisher.anvilformat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class AnvilFormat implements ModInitializer {
	
	private static final Logger LOG = LogManager.getLogger("AnvilFormat");
	
	@Override
	public void onInitialize() {
		LOG.info("Formatting Active :D");
	}
}
