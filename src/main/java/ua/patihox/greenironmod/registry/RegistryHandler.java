package ua.patihox.greenironmod.registry;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ua.patihox.greenironmod.GreenIronMod;
import ua.patihox.greenironmod.registry.blocks.DefaultBlock;
import ua.patihox.greenironmod.registry.blocks.GreenGrinderBlock;
import ua.patihox.greenironmod.registry.blocks.GreenOreBlock;
import ua.patihox.greenironmod.registry.containers.GreenGrinderContainer;
import ua.patihox.greenironmod.registry.items.BasicItem;
import ua.patihox.greenironmod.registry.recipes.GrindingRecipe;
import ua.patihox.greenironmod.registry.tileentities.GreenGrinderTileEntity;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GreenIronMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GreenIronMod.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, GreenIronMod.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, GreenIronMod.MOD_ID);
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GreenIronMod.MOD_ID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //items
    public static final RegistryObject<Item> ANCIENT_PAPER = ITEMS.register("ancient_paper", BasicItem::new);
    public static final RegistryObject<Item> GREEN_GRIT = ITEMS.register("green_grit", BasicItem::new);
    public static final RegistryObject<Item> IRON_GRIT = ITEMS.register("iron_grit", BasicItem::new);
    public static final RegistryObject<Item> GREEN_CRYSTAL = ITEMS.register("green_crystal", BasicItem::new);
    public static final RegistryObject<Item> GREEN_IRON = ITEMS.register("green_iron", BasicItem::new);
    public static final RegistryObject<Item> GREEN_IRON_RAW = ITEMS.register("green_iron_raw", BasicItem::new);
    public static final RegistryObject<Item> GREEN_IRON_GRIT = ITEMS.register("green_iron_grit", BasicItem::new);
    public static final RegistryObject<Item> GREEN_GOO = ITEMS.register("green_goo", BasicItem::new);

    //blocks
    public static final RegistryObject<Block> GREEN_ORE = BLOCKS.register("green_ore", GreenOreBlock::new);
    public static final RegistryObject<Block> GREEN_GRINDER = BLOCKS.register("green_grinder", GreenGrinderBlock::new);
    public static final RegistryObject<Block> GREEN_CRYSTAL_BLOCK = BLOCKS.register("green_crystal_block", DefaultBlock::new);

    //blockItems
    public static final RegistryObject<BlockItem> GREEN_ORE_ITEM = ITEMS.register("green_ore",
            () -> new BlockItem(GREEN_ORE.get(),
                    new Item.Properties().group(GreenIronMod.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> GREEN_GRINDER_ITEM = ITEMS.register("green_grinder",
            () -> new BlockItem(GREEN_GRINDER.get(),
                    new Item.Properties().group(GreenIronMod.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> GREEN_CRYSTAL_BLOCK_ITEM = ITEMS.register("green_crystal_block",
            () -> new BlockItem(GREEN_CRYSTAL_BLOCK.get(),
                    new Item.Properties().group(GreenIronMod.ITEM_GROUP)));

    //tile entities
    public static final RegistryObject<TileEntityType<GreenGrinderTileEntity>> GREEN_GRINDER_TILE_ENTITY = TILE_ENTITY_TYPES.register("green_grinder", () -> TileEntityType.Builder.create(GreenGrinderTileEntity::new, GREEN_GRINDER.get()).build(null));


    //containers
    public static final RegistryObject<ContainerType<GreenGrinderContainer>> GREEN_GRINDER_CONTAINER = CONTAINERS.register("green_grinder", () -> IForgeContainerType.create(GreenGrinderContainer::new));

    //recipes
    public static final RegistryObject<GrindingRecipe.Serializer> GRINDING_SERIALIZER = SERIALIZERS.register("grinding", () -> new GrindingRecipe.Serializer());
    /*public static final RegistryObject<GrindingRecipe.Serializer> GRINDING_SERIALIZER = SERIALIZERS.register("grinding", new Supplier<GrindingRecipe.Serializer>() {
        @Override
        public GrindingRecipe.Serializer get() {
            return new GrindingRecipe.Serializer();
        }
    });*/
    public static final IRecipeType<GrindingRecipe> GRINDING_TYPE = IRecipeType.register(GreenIronMod.MOD_ID + ':' + "grinding");

//    public static final CookingRecipeSerializer<GrindingRecipe> GRINDING_SERIALIZER =
//            IRecipeSerializer.register("grinding", new CookingRecipeSerializer<>(GrindingRecipe::new, 300));
}
