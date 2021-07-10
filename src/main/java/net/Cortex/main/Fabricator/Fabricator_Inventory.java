package net.Cortex.main.Fabricator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface Fabricator_Inventory extends Inventory
{
    DefaultedList<ItemStack> getItems();

    @Override
    default int size() {
        return getItems().size();
    }

    @Override
    default boolean isEmpty() {
        return getItems().stream().allMatch(itemStack -> {
            return itemStack == ItemStack.EMPTY;
        });
    }

    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    @Override
    default ItemStack removeStack(int slot, int amount) {
        ItemStack s = Inventories.splitStack(getItems(), slot, amount);
        markDirty();
        return s;
    }

    @Override
    default ItemStack removeStack(int slot) {
        ItemStack s = Inventories.removeStack(getItems(), slot);
        markDirty();
        return s;
    }

    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if(stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    default void clear() {
        getItems().clear();
        markDirty();
    }

    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
}
