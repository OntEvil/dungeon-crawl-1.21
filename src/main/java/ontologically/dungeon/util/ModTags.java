package ontologically.dungeon.util;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import ontologically.dungeon.DungeonCrawl;
import net.minecraft.item.Item;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(DungeonCrawl.MOD_ID, name));
        }

    }

    public static class Items{
        public static final TagKey<Item> DUNGEON_LOOT = createTag("dungeon_loot");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(DungeonCrawl.MOD_ID, name));
        }
    }
}
