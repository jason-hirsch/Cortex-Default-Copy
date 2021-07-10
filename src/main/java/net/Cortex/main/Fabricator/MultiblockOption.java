package net.Cortex.main.Fabricator;

import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.text.Text;

public class MultiblockOption
{
    public ItemIcon icon;
    public Text title;

    MultiblockOption(ItemIcon icon, Text title) {
        this.icon = icon;
        this.title = title;
    }
}
