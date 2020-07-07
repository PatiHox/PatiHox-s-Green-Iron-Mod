package ua.patihox.greenironmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.patihox.greenironmod.config.GIMConfig;
import ua.patihox.greenironmod.registry.RegistryHandler;
import ua.patihox.greenironmod.world.gen.ModOreGen;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GreenIronMod.MOD_ID)
@Mod.EventBusSubscriber(modid = GreenIronMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GreenIronMod
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "ph_gim";

    public static GreenIronMod instance;

    public static final GreenIronItemGroup ITEM_GROUP = new GreenIronItemGroup(ItemGroup.GROUPS.length,"ph_gim_tab");

    public GreenIronMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GIMConfig.COMMON_SPEC);
        instance = this;
        RegistryHandler.init();
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
        public GreenIronItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.ANCIENT_PAPER.get());
        }
    }

    @SubscribeEvent
    public static void modConfigEvent(final ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == GIMConfig.COMMON_SPEC) {
            GIMConfig.bakeConfig();
        }
    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event){
        ModOreGen.generateOre();
    }
}
