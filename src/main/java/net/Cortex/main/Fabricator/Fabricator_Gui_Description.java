package net.Cortex.main.Fabricator;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.data.Vec2i;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.Cortex.main.MainEntry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Fabricator_Gui_Description extends SyncedGuiDescription
{
    private static final int PROPERTY_COUNT = 2;
    private static final List<MultiblockOption> multiblockOptions = new ArrayList<>();
    private static final TextureIcon backArrow = new TextureIconSubclass(new Identifier("cortex", "textures/block/fabricator/back_arrow.png"));

    public static void addMultiblockOption(Item itemForIcon, Text title) {
        ItemIcon icon = new ItemIconSubclass(itemForIcon);
        multiblockOptions.add(new MultiblockOption(icon, title));
    }

    public Fabricator_Gui_Description(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(MainEntry.FABRICATOR_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, 12), getBlockPropertyDelegate(context, PROPERTY_COUNT));

        setTitlePos(new Vec2i(62, 5));

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);

        root.setSize(175, 185);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot itemSlot = new WItemSlot(blockInventory, 0, 3, 4, false);
        root.add(itemSlot, 0, 10);

        int optionIndex = propertyDelegate.get(0);
        if(optionIndex == -1) {
            displayOptions(root);
        }
        else {
            displayClickedOption(root, optionIndex);
        }

        root.add(createPlayerInventoryPanel(), 0, 85);

        root.validate(this);
    }

    private void displayOptions(WPlainPanel root) {
        final List<WWidget> widgets = new ArrayList<>();

        int x = 57;
        int y = 10;
        int count = 0;
        final int width = 20;
        final int gap = 1;
        final int height = 20;
        final int countPerRow = 5;

        for(int i = 0; i < multiblockOptions.size(); i++) {
            MultiblockOption option = multiblockOptions.get(i);

            WButton button = new WButton();
            button.setAlignment(HorizontalAlignment.CENTER);
            button.setIcon(option.icon);

            widgets.add(button);
            final int finalI = i;
            button.setOnClick(() -> {
                propertyDelegate.set(0, finalI);
                widgets.forEach(root::remove);
                displayClickedOption(root, finalI);
            });

            root.add(button, x, y, width, height);

            x += width + gap;
            count++;
            if(count == countPerRow) {
                x -= count * (width + gap);
                count = 0;
                y += height + gap;
            }
        }
    }

    private void displayClickedOption(WPlainPanel root, int option) {
        final List<WWidget> widgets = new ArrayList<>();

        WLabel title = new WLabel(multiblockOptions.get(option).title);
        title.setHorizontalAlignment(HorizontalAlignment.CENTER);
        widgets.add(title);
        root.add(title, 82, 15, 78, 20);

        WButton button = new WButton();
        button.setIcon(backArrow);
        widgets.add(button);
        button.setOnClick(() -> {
            widgets.forEach(root::remove);
            displayOptions(root);
            propertyDelegate.set(0, -1);
        });
        root.add(button, 57, 10, 20, 20);
    }
}
