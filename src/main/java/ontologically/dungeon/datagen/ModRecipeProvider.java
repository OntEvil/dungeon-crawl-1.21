package ontologically.dungeon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import ontologically.dungeon.DungeonCrawl;
import ontologically.dungeon.block.ModBlocks;
import ontologically.dungeon.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        List<ItemConvertible> STEEL_SMELTABLES = List.of(ModItems.raw_steel);

        //Creates smelting recipe for all things in steel smeltables, that let it smelt to steel
        offerSmelting(exporter, STEEL_SMELTABLES, RecipeCategory.MISC,ModItems.steel,0.25f,250,"steel");
        offerBlasting(exporter, STEEL_SMELTABLES, RecipeCategory.MISC,ModItems.steel,0.25f,150,"steel");




        //creates reversible recipe like iron and iron block
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.steel, RecipeCategory.BUILDING_BLOCKS, ModBlocks.steel_block);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.metallurgic_coal, RecipeCategory.BUILDING_BLOCKS, ModBlocks.metallurgic_coal_block);


        //How to make a shaped recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.raw_steel_block)
                //gives second parameter if given imput in pattern.
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.raw_steel)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.raw_steel),conditionsFromItem(ModItems.raw_steel))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.steel_sword)
                //gives second parameter if given imput in pattern.
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.steel_pickaxe)
                //gives second parameter if given imput in pattern.
                .pattern("###")
                .pattern(" s ")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.steel_axe)
                //gives second parameter if given imput in pattern.
                .pattern("## ")
                .pattern("#s ")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter, Identifier.of(DungeonCrawl.MOD_ID, "steel_axe_b"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.steel_axe)
                //gives second parameter if given imput in pattern.
                .pattern(" ##")
                .pattern(" s#")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter, Identifier.of(DungeonCrawl.MOD_ID, "steel_axe_a"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.steel_shovel)
                //gives second parameter if given imput in pattern.
                .pattern(" # ")
                .pattern(" s ")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.steel_hoe)
                //gives second parameter if given imput in pattern.
                .pattern("## ")
                .pattern(" s ")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter, Identifier.of(DungeonCrawl.MOD_ID, "steel_hoe_a"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.steel_hoe)
                //gives second parameter if given imput in pattern.
                .pattern(" ##")
                .pattern(" s ")
                .pattern(" s ")
                .input('#', ModItems.steel)
                .input('s', Items.STICK)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter, Identifier.of(DungeonCrawl.MOD_ID, "steel_hoe_b"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.steel_smithing_template,2)
                //gives second parameter if given imput in pattern.
                .pattern("i#i")
                .pattern("isi")
                .pattern("iii")
                .input('#', ModItems.steel_smithing_template)
                .input('i', Items.IRON_INGOT)
                .input('s', Items.STONE)
                //unlocks recipe as soon as we get raw steel
                .criterion(hasItem(ModItems.steel),conditionsFromItem(ModItems.steel))
                .offerTo(exporter);




        //shapeless recipe builder
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.raw_steel, 9)
                //gives second parameter if given input
                //when changing block change all instances
                //criterion determines when you unlock recipe
                .input(ModBlocks.raw_steel_block)
                .criterion(hasItem(ModBlocks.raw_steel_block), conditionsFromItem(ModBlocks.raw_steel_block))
                .offerTo(exporter, Identifier.of(DungeonCrawl.MOD_ID, "raw_steel_from_raw_steel_block"));




    }



}
