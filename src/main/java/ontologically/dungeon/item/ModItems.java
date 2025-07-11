package ontologically.dungeon.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import ontologically.dungeon.DungeonCrawl;

public class ModItems {
    public static final Item steel = registerItem("steel", new Item(new Item.Settings()));

    public static final Item raw_steel = registerItem("raw_steel", new Item(new Item.Settings()));

    public static final Item steel_fragment = registerItem("steel_fragment", new Item(new Item.Settings()));

    public static final Item metallurgic_coal = registerItem("metallurgic_coal", new Item(new Item.Settings()));

    public static final Item steel_sword = registerItem("steel_sword",
            new SwordItem(ModToolMaterials.Steel, new Item.Settings()
                    //the base attack and attack speed are the default for all swords in the game
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.Steel, 3,-2.4f))));

    public static final Item steel_pickaxe = registerItem("steel_pickaxe",
            new PickaxeItem(ModToolMaterials.Steel, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.Steel, 1,-2.8f))));

    public static final Item steel_shovel = registerItem("steel_shovel",
            new ShovelItem(ModToolMaterials.Steel, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.Steel, 1.5f,-3.0f))));

    public static final Item steel_axe = registerItem("steel_axe",
            new AxeItem(ModToolMaterials.Steel, new Item.Settings()
                    //the base attack and attack speed are the default for all swords in the game
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.Steel, 6,-3.2f))));

    public static final Item steel_hoe = registerItem("steel_hoe",
            new HoeItem(ModToolMaterials.Steel, new Item.Settings()
                    //the base attack and attack speed are the default for all swords in the game
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.Steel, 0,-3.0f))));

    public static final Item steel_smithing_template = registerItem("steel_smithing_template", new Item(new Item.Settings()));


    public static final Item steel_helmet = registerItem("steel_helmet",
            new ArmorItem(ModArmorMaterials.steel_armor_material, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(25))));

    public static final Item steel_chestplate = registerItem("steel_chestplate",
            new ArmorItem(ModArmorMaterials.steel_armor_material, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))));

    public static final Item steel_leggings = registerItem("steel_leggings",
            new ArmorItem(ModArmorMaterials.steel_armor_material, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(25))));

    public static final Item steel_boots = registerItem("steel_boots",
            new ArmorItem(ModArmorMaterials.steel_armor_material, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(25))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonCrawl.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DungeonCrawl.LOGGER.info("Registering mod items for " + DungeonCrawl.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(steel);
            fabricItemGroupEntries.add(raw_steel);
            fabricItemGroupEntries.add(metallurgic_coal);
            fabricItemGroupEntries.add(steel_fragment);

        });
    }
}
