package ontologically.dungeon.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import ontologically.dungeon.DungeonCrawl;
import ontologically.dungeon.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ontologically.dungeon.DungeonCrawl;

public class ModItemGroups {
    // Method that helps tag items in our mod
    public static final ItemGroup RAW_STEEL_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(DungeonCrawl.MOD_ID, "raw_steel_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.raw_steel))
                    .displayName(Text.translatable("itemgroup.dungeoncrawl.raw_steel_items"))
                    .entries((displayContext, entries) -> {
                        //this adds each item to the raw steel items tag
                        entries.add(ModItems.raw_steel);
                        entries.add(ModItems.steel);

                    }).build());

    //method that helps tag blocks in our mod
    public static final ItemGroup RAW_STEEL_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(DungeonCrawl.MOD_ID, "raw_steel_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.raw_steel_block))
                    .displayName(Text.translatable("itemgroup.dungeoncrawl.raw_steel_blocks"))
                    .entries((displayContext, entries) -> {
                        //This adds each block to the raw steel blocks tag
                        entries.add(ModBlocks.raw_steel_block);
                        entries.add(ModBlocks.steel_block);

                    }).build());

    public static void registerItemGroups(){
        DungeonCrawl.LOGGER.info("Registering Item Groups for " + DungeonCrawl.MOD_ID);
    }
}

