package ua.patihox.greenironmod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.patihox.greenironmod.init.BlockInit;
import ua.patihox.greenironmod.init.ItemInit;
import ua.patihox.greenironmod.world.gen.ModOreGen;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("phs-greenironmod")
@Mod.EventBusSubscriber(modid = GreenIronMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GreenIronMod
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "phs-greenironmod";

    public static GreenIronMod instance;

    public GreenIronMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("PatiHox is sharpening his green sword");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static class GreenIronItemGroup extends ItemGroup{
        public static final GreenIronItemGroup instance = new GreenIronItemGroup(ItemGroup.GROUPS.length,"phs-greenirontab");

        public GreenIronItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.example_item);
        }
    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event){
        ModOreGen.generateOre();
    }
}
