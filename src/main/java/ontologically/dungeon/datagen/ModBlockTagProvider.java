package ontologically.dungeon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import ontologically.dungeon.block.ModBlocks;
import ontologically.dungeon.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //all blocks that need a pickaxe to mine
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.steel_block)
                .add(ModBlocks.metallurgic_coal_block)
                .add(ModBlocks.raw_steel_block);
        //what tier tool you need to mine
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.raw_steel_block)
                .add(ModBlocks.steel_block);
    /**
        getOrCreateTagBuilder(ModTags.Blocks.needs_steel_tool)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
     */
    }
}
