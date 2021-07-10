package net.Cortex.main.Ore_Generator;

import net.Cortex.main.MainEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.state.property.DirectionProperty;

public class Ore_Generator_Controller extends Block implements BlockEntityProvider
{
    public static final DirectionProperty FACING = Properties.FACING;

    public Ore_Generator_Controller(Settings settings)
    {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        Direction direction = ctx.getPlayerFacing().getOpposite();
        BlockState state = super.getPlacementState(ctx);
        if(state != null) {
            state = state.with(FACING, direction);
        }
        return state;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new Ore_Generator_Controller_Entity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return Ore_Generator_Controller_Entity::tick;
    }
}
