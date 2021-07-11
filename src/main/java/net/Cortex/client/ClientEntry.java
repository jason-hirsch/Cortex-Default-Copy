package net.Cortex.client;

import net.Cortex.main.Fabricator.Fabricator_Gui_Description;
import net.Cortex.main.Fabricator.Fabricator_Screen;
import net.Cortex.main.MainEntry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ClientEntry implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ScreenRegistry.register(MainEntry.FABRICATOR_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new Fabricator_Screen(gui, inventory.player, title));
        BlockEntityRendererRegistry.INSTANCE.register(MainEntry.fabricator_entity, Fabricator_Renderer::new);
    }
}
