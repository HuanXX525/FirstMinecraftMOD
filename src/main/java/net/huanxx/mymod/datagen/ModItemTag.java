package net.huanxx.mymod.datagen;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.item.ModItems;
import net.huanxx.mymod.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTag extends ItemTagsProvider {
    public ModItemTag(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.ALICE_MAGIC_BLOCK_TRANSFORMABLE)
                .add(ModItems.HUANXX_1_ITEM.get())
                .add(Items.GOLD_INGOT.asItem());
    }
}
