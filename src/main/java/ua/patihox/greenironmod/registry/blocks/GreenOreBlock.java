package ua.patihox.greenironmod.registry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GreenOreBlock extends Block {
    public GreenOreBlock() {
        super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).hardnessAndResistance(3.0F, 3.0F));
    }
}
