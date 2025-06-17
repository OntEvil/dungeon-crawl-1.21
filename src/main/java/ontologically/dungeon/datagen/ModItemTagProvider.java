package ontologically.dungeon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import ontologically.dungeon.item.ModItems;
import ontologically.dungeon.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.DUNGEON_LOOT)
                .add(ModItems.steel)
                .add(ModItems.raw_steel)
                .add(Items.COAL)
                .add(Items.IRON_INGOT);


        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.steel_sword);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.steel_pickaxe);
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.steel_shovel);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.steel_axe);
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.steel_hoe);



    }

}
