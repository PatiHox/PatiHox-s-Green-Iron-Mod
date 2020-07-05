package ua.patihox.greenironmod.world.gen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import ua.patihox.greenironmod.config.GIMConfig;
import ua.patihox.greenironmod.registry.RegistryHandler;

public class ModOreGen {
    public static void generateOre(){
        for(Biome biome: ForgeRegistries.BIOMES){
            ConfiguredPlacement customConfig = Placement.COUNT_RANGE
                    .configure(new CountRangeConfig(GIMConfig.greenOreGenCount,
                            GIMConfig.greenOreGenBottomOffset,
                            GIMConfig.greenOreGenTopOffset,
                            GIMConfig.greenOreGenMaximum));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
                            .withConfiguration(new OreFeatureConfig(OreFeatureConfig
                                    .FillerBlockType.NATURAL_STONE,
                                    RegistryHandler.GREEN_ORE.get().getDefaultState(), 4))
                            .withPlacement(customConfig));
        }
    }
}
