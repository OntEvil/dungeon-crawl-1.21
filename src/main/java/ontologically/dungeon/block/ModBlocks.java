package ontologically.dungeon.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import ontologically.dungeon.DungeonCrawl;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //Each block is a method with parameters controlling how the block functions
    public static final Block raw_steel_block = registerBlock("raw_steel_block",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.ANVIL)));

    //a helper method called in ModBlocks to register blocks we create
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DungeonCrawl.MOD_ID, name), block);
    }
    //A helper method to register items we create
    private static void  registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(DungeonCrawl.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    //A method that registers blocks into the appropriate tag
    public static void registerModBlocks() {
        DungeonCrawl.LOGGER.info("Registering mod blocks for " + DungeonCrawl.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
                    fabricItemGroupEntries.add(ModBlocks.raw_steel_block);
                }
        );

    }

}
