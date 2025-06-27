package ontologically.dungeon.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import ontologically.dungeon.DungeonCrawl;
import ontologically.dungeon.screen.custom.BlackStoneForgeScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<BlackStoneForgeScreenHandler> blackstone_forge_screen_handler =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(DungeonCrawl.MOD_ID, "blackstone_forge_screen_handler"),
                    new ExtendedScreenHandlerType<>(BlackStoneForgeScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandler() {
        DungeonCrawl.LOGGER.info("Registering screen handler for " + DungeonCrawl.MOD_ID);
    }

}
