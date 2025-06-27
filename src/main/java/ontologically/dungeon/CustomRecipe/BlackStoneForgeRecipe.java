package ontologically.dungeon.CustomRecipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record BlackStoneForgeRecipe (Ingredient inputItem, ItemStack output) implements Recipe<BlackStoneForgeRecipeInput>{

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read recipe JSON file --> new BlackStoneForgeRecipe

    @Override
    public boolean matches(BlackStoneForgeRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem().test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(BlackStoneForgeRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BLACKSTONE_FORGE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BLACKSTONE_FORGE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<BlackStoneForgeRecipe> {
        public static final MapCodec<BlackStoneForgeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(BlackStoneForgeRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(BlackStoneForgeRecipe::output)
        ).apply(inst, BlackStoneForgeRecipe::new));

        public static final PacketCodec<RegistryByteBuf, BlackStoneForgeRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, BlackStoneForgeRecipe::inputItem,
                        ItemStack.PACKET_CODEC, BlackStoneForgeRecipe::output,
                        BlackStoneForgeRecipe::new);

        @Override
        public MapCodec<BlackStoneForgeRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, BlackStoneForgeRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
