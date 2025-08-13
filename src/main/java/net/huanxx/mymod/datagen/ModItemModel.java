package net.huanxx.mymod.datagen;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModel extends ItemModelProvider {

    public ModItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.HUANXX_1_ITEM.get());
        basicItem(ModItems.HUANXX_GET_GOLD_TOOL.get());
        basicItem(ModItems.TEST_ITEM.get());
    }
}
