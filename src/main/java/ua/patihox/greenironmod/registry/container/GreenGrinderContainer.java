package ua.patihox.greenironmod.registry.container;

import net.minecraft.block.ChestBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import ua.patihox.greenironmod.registry.RegistryHandler;
import ua.patihox.greenironmod.registry.tileentities.GreenGrinderTileEntity;

import javax.annotation.Nullable;
import java.util.Objects;

public class GreenGrinderContainer extends Container {
    private final int numSlotsCount = 2;
    public final GreenGrinderTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public GreenGrinderContainer(final int windowId, final PlayerInventory playerInventory, final GreenGrinderTileEntity tileEntity){
        super(RegistryHandler.GREEN_GRINDER_CONTAINER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        //Main inv
        this.addSlot(new Slot(tileEntity, 0, 56, 17));
        this.addSlot(new Slot(tileEntity, 0, 112, 31));
        //Player inv
        int startPlayerInvX = 8;
        int startPlayerInvY = 84;
        int cellSizePlus2 = 18;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory,
                        9 + (row * 9) + column,
                        startPlayerInvX + cellSizePlus2 * column,
                        startPlayerInvY + cellSizePlus2 * row));
            }
        }
        //Hotbar
        int startHotbarX = 8;
        int startHotbarY = 142;

        for (int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInventory, column, startHotbarX + cellSizePlus2 * column, startHotbarY));
        }
    }

    private static GreenGrinderTileEntity getTileEntity(final PlayerInventory inventory, final PacketBuffer data){
        Objects.requireNonNull(inventory, "inventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = inventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof GreenGrinderTileEntity){
            return (GreenGrinderTileEntity)tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct!" + tileAtPos);
    }

    public GreenGrinderContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId,playerInventory, getTileEntity(playerInventory,data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, RegistryHandler.GREEN_GRINDER.get());
    }



    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.numSlotsCount) {
                if (!this.mergeItemStack(itemstack1, this.numSlotsCount, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.numSlotsCount, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
