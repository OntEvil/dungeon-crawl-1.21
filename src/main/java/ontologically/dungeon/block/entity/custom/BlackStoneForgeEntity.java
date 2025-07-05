package ontologically.dungeon.block.entity.custom;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ontologically.dungeon.item.ModItems;
import ontologically.dungeon.recipe.BlackStoneForgeRecipe;
import ontologically.dungeon.recipe.BlackStoneForgeRecipeInput;
import ontologically.dungeon.recipe.ModRecipes;
import ontologically.dungeon.block.entity.ImplementedInventory;
import ontologically.dungeon.block.entity.ModBlockEntities;
import ontologically.dungeon.screen.custom.BlackStoneForgeScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

import static ontologically.dungeon.block.Custom.BlackStoneForgeBlock.lit;

public class BlackStoneForgeEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);



    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int FUEL_SLOT = 3;
    private int smeltCount = 0;
    private int fuelTime = 0;


    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;


    public BlackStoneForgeEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.blackstone_forge_BE, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlackStoneForgeEntity.this.progress;
                    case 1 -> BlackStoneForgeEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        BlackStoneForgeEntity.this.progress = value;
                    case 1:
                        BlackStoneForgeEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("blackstone_forge.progress", progress);
        nbt.putInt("blackstone_forge.maxProgress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("blackstone_forge.progress");
        maxProgress = nbt.getInt("blackstone_forge.maxProgress");
        super.readNbt(nbt, registryLookup);
    }

    /**
     * - check if valid recipe
     * -if valid
     * - check if has fuel
     * - if true
     * -increase crafting progress
     * -if done crafting, craft item and reset progress.
     */
    public void tick(World world, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            if (isForgeHeated()) {
                useFuel();
                world.setBlockState(pos,state.with(lit,true));
                increaseCraftingProgress();

            }
            markDirty(world, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
                world.setBlockState(pos,state.with(lit,false));
            }

        } else {
            resetProgress();
        }
    }

    private boolean isVanilla(Item item) {
        return (item.toString().contains("minecraft"));
    }

    //true is succesfull
    //false if could not see item as fuel or is no item.
    private void useFuel() {
        ItemStack fuelStack = this.getStack(FUEL_SLOT);
        if (fuelTime == 0) {
            this.removeStack(FUEL_SLOT, 1);
            if (isVanilla(fuelStack.getItem())) {
                Map<Item, Integer> map = AbstractFurnaceBlockEntity.createFuelTimeMap();
                fuelTime = map.get(fuelStack.getItem());
            } else if ((fuelStack.getItem() == ModItems.metallurgic_coal)) {
                fuelTime = 3000;
            }
        }

    }




    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    private void craftItem() {
        smeltCount ++;
        if(smeltCount > 4) {
            this.removeStack(INPUT_SLOT2,1);
            smeltCount = 0;
        }
        Optional<RecipeEntry<BlackStoneForgeRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        this.removeStack(INPUT_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        if(isForgeHeated()) {
            fuelTime -= 2;
            this.progress++;
        }
    }

    private boolean isFuel(Item item){
        if(AbstractFurnaceBlockEntity.canUseAsFuel(this.getStack(FUEL_SLOT))){
            return true;
        }
        return false;
    }

    private boolean isForgeHeated() {
        if(this.getStack(FUEL_SLOT).isEmpty()){
            return false;
        } else if (this.isFuel(this.getStack(FUEL_SLOT).getItem())) {
            return true;
        } else if (this.getStack(FUEL_SLOT).getRegistryEntry() == ModItems.metallurgic_coal.getRegistryEntry()) {
            return true;
        }
        return false;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<BlackStoneForgeRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }
        if(!metallurgicCoalSupplied()) {
            return false;
        }
        ItemStack output = recipe.get().value().output();

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
        //code later #58 16:04c
    }

    private boolean metallurgicCoalSupplied() {
        if(this.getStack(INPUT_SLOT2).getItem() == ModItems.metallurgic_coal) {
            return true;
        }
        return false;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }


    private Optional<RecipeEntry<BlackStoneForgeRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager()
                .getFirstMatch(ModRecipes.BLACKSTONE_FORGE_TYPE, new BlackStoneForgeRecipeInput(inventory.get(INPUT_SLOT)), this.getWorld());
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket(){
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }


    @Override
    public Text getDisplayName() {
        return Text.translatable("block.dungeoncrawl.blackstone_forge");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BlackStoneForgeScreenHandler(syncId,playerInventory, this, this.propertyDelegate);
    }
}
