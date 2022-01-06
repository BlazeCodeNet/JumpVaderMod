package net.blazecode.jumpvaders;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.blazecode.jumpvaders.registry.BlockRegister;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment( EnvType.SERVER )
public class JumpVaderMod implements DedicatedServerModInitializer
{

	public static final String MODID = "jumpvader";

	@Override
	public void onInitializeServer( )
	{
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

		BlockRegister.init();
	}
	
	public static ModConfig getConfig()
	{
		if (config == null)
		{
			config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		}
		return config;
	}
	private static ModConfig config;
	
	@Config(name = MODID)
	public static class ModConfig implements ConfigData
	{
		@Comment("Toggles the entire mod on or off; !THIS ISNT IMPLIMENTED YET!")
		boolean enabled = true;
	}
}
