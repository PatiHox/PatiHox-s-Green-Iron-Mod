package ua.patihox.greenironmod.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ua.patihox.greenironmod.GreenIronMod;
import ua.patihox.greenironmod.registry.blocks.GreenOreBlock;
import ua.patihox.greenironmod.registry.items.BasicItem;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, GreenIronMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, GreenIronMod.MOD_ID);


    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
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

    //blockItems
    public static final RegistryObject<BlockItem> GREEN_ORE_ITEM = ITEMS.register("green_ore",
            () -> new BlockItem(GREEN_ORE.get(),
                new Item.Properties().group(GreenIronMod.ITEM_GROUP)));

}
