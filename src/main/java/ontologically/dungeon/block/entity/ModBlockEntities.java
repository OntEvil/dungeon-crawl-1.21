package ontologically.dungeon.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ontologically.dungeon.DungeonCrawl;
import ontologically.dungeon.block.ModBlocks;
import ontologically.dungeon.block.entity.custom.BlackStoneForgeEntity;

public class ModBlockEntities {
    public static final BlockEntityType<BlackStoneForgeEntity> blackstone_forge_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(DungeonCrawl.MOD_ID, "blackstone_forge_be"),
                    BlockEntityType.Builder.create(BlackStoneForgeEntity::new, ModBlocks.blackstone_forge).build(null));

    public static void registerBlockEntities() {
        DungeonCrawl.LOGGER.info("Registering Block Entities for " + DungeonCrawl.MOD_ID);
    }
}
