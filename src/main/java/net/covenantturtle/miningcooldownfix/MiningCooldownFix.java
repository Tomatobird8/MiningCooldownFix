package net.covenantturtle.miningcooldownfix;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class MiningCooldownFix implements ModInitializer {
	
	
	public static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "miningcooldownfix";
	public static final String MOD_NAME = "Mining Cooldown Fix";
	
	@Override
	public void onInitialize() {
		AutoConfig.register(MyConfig.class,JanksonConfigSerializer::new);
	}

	public static void log(Level level, String message) {
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}
	
	public static MyConfig getConfig() {
		return AutoConfig.getConfigHolder(MyConfig.class).getConfig();
	}
	
}
