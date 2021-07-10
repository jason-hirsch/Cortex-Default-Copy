package net.Cortex.main.Fabricator;

import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TextureIconSubclass extends TextureIcon
{
    public TextureIconSubclass(Identifier texture)
    {
        super(texture);
    }

    @Override
    public void paint(MatrixStack matrices, int x, int y, int size)
    {
        super.paint(matrices, x + 1, y + 1, size);
    }
}
