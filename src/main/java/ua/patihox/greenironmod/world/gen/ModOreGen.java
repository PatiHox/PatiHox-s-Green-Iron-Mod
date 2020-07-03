package ua.patihox.greenironmod.world.gen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import ua.patihox.greenironmod.init.BlockInit;

public class ModOreGen {
    public static void generateOre(){
        for(Biome biome: ForgeRegistries.BIOMES){
            ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(10,5,5,20));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.green_ore.getDefaultState(), 4)).withPlacement(customConfig));
        }
    }
}
