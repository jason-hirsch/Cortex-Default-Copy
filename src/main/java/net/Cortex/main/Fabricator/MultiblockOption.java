package net.Cortex.main.Fabricator;

import com.google.common.collect.ImmutableMap;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiblockOption
{
    public ItemIcon icon;
    public Text title;

    MultiblockOption(ItemIcon icon, Text title) {
        this.icon = icon;
        this.title = title;
    }

    public ImmutableMap<BlockPos, Block> getMultiblock(int size) {
        ConcurrentHashMap<BlockPos, Block> multiblock = new ConcurrentHashMap<>();

        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                for(int z = -1; z <= 1; z++) {
                    multiblock.put(new BlockPos(x, y, z), Blocks.IRON_BLOCK);
                }
            }
        }

        return ImmutableMap.copyOf(multiblock);
    }
}
