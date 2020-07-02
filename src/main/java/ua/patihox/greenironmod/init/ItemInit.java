package ua.patihox.greenironmod.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;
import ua.patihox.greenironmod.GreenIronMod;

@Mod.EventBusSubscriber(modid = GreenIronMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(GreenIronMod.MOD_ID)
public class ItemInit {
    public static final Item example_item = null;
    public static final Item green_grit = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new Item(new Item.Properties().group(GreenIronMod.GreenIronItemGroup.instance)).setRegistryName("example_item"));
        event.getRegistry().register(new Item(new Item.Properties().group(GreenIronMod.GreenIronItemGroup.instance)).setRegistryName("green_grit"));
    };
}
