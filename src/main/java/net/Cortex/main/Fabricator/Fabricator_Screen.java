package net.Cortex.main.Fabricator;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class Fabricator_Screen extends CottonInventoryScreen<Fabricator_Gui_Description> {

    public Fabricator_Screen(Fabricator_Gui_Description description, PlayerEntity player, Text title)
    {
        super(description, player, title);
    }
}
