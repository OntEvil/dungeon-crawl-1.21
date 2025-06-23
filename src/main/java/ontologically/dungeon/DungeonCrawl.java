package ontologically.dungeon;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import ontologically.dungeon.block.ModBlocks;
import ontologically.dungeon.item.ModItemGroups;
import ontologically.dungeon.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonCrawl implements ModInitializer {
	public static final String MOD_ID = "dungeoncrawl";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		FuelRegistry.INSTANCE.add(ModItems.metallurgic_coal, 3000);

		LOGGER.info("Hello Fabric world!");

	}
}