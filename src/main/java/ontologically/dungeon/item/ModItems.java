package ontologically.dungeon.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ontologically.dungeon.DungeonCrawl;

public class ModItems {
    public static final Item steel = registerItem("steel", new Item(new Item.Settings()));

    public static final Item raw_steel = registerItem("raw_steel", new Item(new Item.Settings()));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonCrawl.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DungeonCrawl.LOGGER.info("Registering mod items for " + DungeonCrawl.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(steel);
            fabricItemGroupEntries.add(raw_steel);

        });
    }
}
