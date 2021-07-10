package net.Cortex.main.Ore_Generator;

import net.Cortex.main.MainEntry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Ore_Generator_Controller_Entity extends BlockEntity
{
    public Ore_Generator_Controller_Entity(BlockPos pos, BlockState state) { super(MainEntry.ore_generator_controller_entity, pos, state); }

    public static <T extends BlockEntity> void tick(World world, BlockPos blockPos, BlockState blockState, T t) {
    }
}
