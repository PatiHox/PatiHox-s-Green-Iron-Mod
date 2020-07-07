package ua.patihox.greenironmod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;
import ua.patihox.greenironmod.GreenIronMod;

@Mod.EventBusSubscriber(modid = GreenIronMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GIMConfig {

    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static int greenOreGenCount;
    public static int greenOreGenBottomOffset;
    public static int greenOreGenTopOffset;
    public static int greenOreGenMaximum;
    public static int greenOreGenVeinSize;

    public static void bakeConfig(){
        greenOreGenCount = COMMON.getGreenOreGenCount().get();
        greenOreGenBottomOffset = COMMON.getGreenOreGenBottomOffset().get();
        greenOreGenTopOffset = COMMON.getGreenOreGenTopOffset().get();
        greenOreGenMaximum = COMMON.getGreenOreGenMaximum().get();
        greenOreGenVeinSize = COMMON.getGreenOreGenVeinSize().get();
    }

    public static class CommonConfig {
        private final ForgeConfigSpec.ConfigValue<Integer> greenOreGenCount;
        private final ForgeConfigSpec.ConfigValue<Integer> greenOreGenBottomOffset;
        private final ForgeConfigSpec.ConfigValue<Integer> greenOreGenTopOffset;
        private final ForgeConfigSpec.ConfigValue<Integer> greenOreGenMaximum;
        private final ForgeConfigSpec.ConfigValue<Integer> greenOreGenVeinSize;

        public ForgeConfigSpec.ConfigValue<Integer> getGreenOreGenCount() {
            return greenOreGenCount;
        }

        public ForgeConfigSpec.ConfigValue<Integer> getGreenOreGenBottomOffset() {
            return greenOreGenBottomOffset;
        }

        public ForgeConfigSpec.ConfigValue<Integer> getGreenOreGenTopOffset() {
            return greenOreGenTopOffset;
        }

        public ForgeConfigSpec.ConfigValue<Integer> getGreenOreGenMaximum() {
            return greenOreGenMaximum;
        }

        public ForgeConfigSpec.ConfigValue<Integer> getGreenOreGenVeinSize() {
            return greenOreGenVeinSize;
        }

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Green iron mod configuration").push("ore gen");
            builder.push("green ore");
            greenOreGenCount = builder.comment("Amount of veins per chunk")
                    .translation(GreenIronMod.MOD_ID+".config."+"greenOreGenCount")
                    .define("greenOreGenCount", 10);
            greenOreGenBottomOffset = builder.comment("Ore generation bottom offset")
                    .translation(GreenIronMod.MOD_ID+".config."+"greenOreGenBottomOffset")
                    .define("greenOreGenBottomOffset", 5);
            greenOreGenTopOffset = builder.comment("Ore generation top offset. ")
                    .translation(GreenIronMod.MOD_ID+".config."+"greenOreGenTopOffset")
                    .define("greenOreGenTopOffset", 5);
            greenOreGenMaximum = builder.comment("Ore generation maximum height")
                    .translation(GreenIronMod.MOD_ID+".config."+"greenOreGenMaximum")
                    .define("greenOreGenMaximum", 20);
            greenOreGenVeinSize = builder.comment("Amount of ore blocks per vein")
                    .translation(GreenIronMod.MOD_ID+".config."+"greenOreGenVeinSize")
                    .define("greenOreGenVeinSize", 4);
            builder.pop();
            builder.pop();
        }
    }
}
