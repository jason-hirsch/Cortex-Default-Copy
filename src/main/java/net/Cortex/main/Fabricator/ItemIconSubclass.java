package net.Cortex.main.Fabricator;

import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;

public class ItemIconSubclass extends ItemIcon
{
    public ItemIconSubclass(Item item)
    {
        super(item);
    }

    @Override
    public void paint(MatrixStack matrices, int x, int y, int size)
    {
        super.paint(matrices, x + 1, y + 1, 16);
    }
}
