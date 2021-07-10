package net.Cortex.main.Fabricator;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.Cortex.main.MainEntry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class Fabricator_Entity extends BlockEntity implements Fabricator_Inventory, SidedInventory, InventoryProvider, PropertyDelegateHolder, ExtendedScreenHandlerFactory
{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(12, ItemStack.EMPTY);
    private int multiblockOptionIs = 0;
    private int multiblockOptionShouldBe = -1;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate()
    {
        @Override
        public int get(int index)
        {
            if(index == 0) return multiblockOptionIs;
            else if(index == 1) return multiblockOptionShouldBe;
            return -2;
        }

        @Override
        public void set(int index, int value)
        {
            if(index == 0) multiblockOptionIs = value;
            else if(index == 1) multiblockOptionShouldBe = value;
        }

        @Override
        public int size() { return 2; }
    };

    @Override
    public PropertyDelegate getPropertyDelegate() { return propertyDelegate; }

    public Fabricator_Entity(BlockPos pos, BlockState state) {
        super(MainEntry.fabricator_entity, pos, state);
    }

    public static <T extends BlockEntity> void tick(World world, BlockPos blockPos, BlockState blockState, T t) {
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return items;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos)
    {
        return (SidedInventory)world.getBlockEntity(pos);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt)
    {
        Inventories.writeNbt(nbt, items);
        return super.writeNbt(nbt);
    }

    @Override
    public int[] getAvailableSlots(Direction side)
    {
        return IntStream.range(0, size()).toArray();
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir)
    {
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir)
    {
        return true;
    }

    @Override
    public Text getDisplayName()
    {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player)
    {
        return new Fabricator_Gui_Description(syncId, inv, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(pos);
    }
}
