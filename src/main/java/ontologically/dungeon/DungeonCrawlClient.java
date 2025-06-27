package ontologically.dungeon;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import ontologically.dungeon.screen.ModScreenHandlers;
import ontologically.dungeon.screen.custom.BlackStoneForgeScreen;

public class DungeonCrawlClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        HandledScreens.register(ModScreenHandlers.blackstone_forge_screen_handler, BlackStoneForgeScreen::new);

    }
}
