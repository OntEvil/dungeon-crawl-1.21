package ontologically.dungeon.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ontologically.dungeon.DungeonCrawl;

public class ModRecipes {
    public static final RecipeSerializer<BlackStoneForgeRecipe> BLACKSTONE_FORGE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(DungeonCrawl.MOD_ID, "blackstone_forge"),
            new BlackStoneForgeRecipe.Serializer());

    public static final RecipeType<BlackStoneForgeRecipe> BLACKSTONE_FORGE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(DungeonCrawl.MOD_ID, "blackstone_forge"), new RecipeType<BlackStoneForgeRecipe>() {
                @Override
                public String toString() {
                    return "blackstone_forge";
                }});

    public static void registerRecipes() {
        DungeonCrawl.LOGGER.info("Registering Custom Recipes for " + DungeonCrawl.MOD_ID);
    }
}
