package ontologically.dungeon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import ontologically.dungeon.block.ModBlocks;
import ontologically.dungeon.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    //generates a basic block model json and item json
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.raw_steel_block);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.steel_block);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.raw_steel, Models.GENERATED);
        itemModelGenerator.register(ModItems.steel, Models.GENERATED);

        itemModelGenerator.register(ModItems.steel_sword, Models.HANDHELD);
        itemModelGenerator.register(ModItems.steel_shovel, Models.HANDHELD);
        itemModelGenerator.register(ModItems.steel_axe, Models.HANDHELD);
        itemModelGenerator.register(ModItems.steel_pickaxe, Models.HANDHELD);
        itemModelGenerator.register(ModItems.steel_hoe, Models.HANDHELD);


    }
}
