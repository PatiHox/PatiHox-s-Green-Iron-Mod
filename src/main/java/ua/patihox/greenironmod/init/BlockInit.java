package ua.patihox.greenironmod.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import ua.patihox.greenironmod.GreenIronMod;

@ObjectHolder(GreenIronMod.MOD_ID)
@Mod.EventBusSubscriber(modid = GreenIronMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final OreBlock green_ore = null;

    @SubscribeEvent
    public static void registerBlock(final RegistryEvent.Register<Block> event){
        event.getRegistry().register(new OreBlock(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).hardnessAndResistance(3.0F, 3.0F)).setRegistryName("green_ore"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(green_ore, new Item.Properties().group(GreenIronMod.GreenIronItemGroup.instance)).setRegistryName(green_ore.getRegistryName()));
    }
}
