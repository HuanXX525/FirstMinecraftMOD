package net.huanxx.mymod.datagen;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.data.PackOutput;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockState extends BlockStateProvider {


    public ModBlockState(PackOutput output, ExistingFileHelper exFileHelper) {
        // 直接使用自己的MOD_ID
        super(output, MyMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItemAllCube(ModBlocks.MAGIC_BLOCK);
        blockWithItemAllCube(ModBlocks.HUANXX_1_BLOCK);
    }

    private void blockWithItemAllCube(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
