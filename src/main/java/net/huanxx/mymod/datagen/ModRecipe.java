package net.huanxx.mymod.datagen;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.block.ModBlocks;
import net.huanxx.mymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipe extends RecipeProvider {
    public ModRecipe(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HUANXX_1_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HUANXX_1_ITEM.get())
                .unlockedBy(getHasName(ModItems.HUANXX_1_ITEM.get()), has(ModItems.HUANXX_1_ITEM.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HUANXX_1_ITEM.get(), 9)
                .requires(ModBlocks.HUANXX_1_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.HUANXX_1_BLOCK.get()), has(ModBlocks.HUANXX_1_BLOCK.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HUANXX_GET_GOLD_TOOL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA").define('A', Items.GOLD_INGOT).define('B', ModItems.HUANXX_1_ITEM.get()).unlockedBy(getHasName(ModItems.HUANXX_1_ITEM.get()), has(ModItems.HUANXX_1_ITEM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK.get())
                .pattern("  A")
                .pattern("BA ")
                .pattern("AB ").define('A', Items.DIAMOND).define('B', ModItems.HUANXX_1_ITEM.get()).unlockedBy(getHasName(ModItems.HUANXX_1_ITEM.get()), has(ModItems.HUANXX_1_ITEM.get())).save(pRecipeOutput);
    }


    /**
     * 覆写父类方法将食谱放置在MOD_ID文件夹下
     * @param pRecipeOutput
     * @param pSerializer
     * @param pRecipeFactory
     * @param pIngredients
     * @param pCategory
     * @param pResult
     * @param pExperience
     * @param pCookingTime
     * @param pGroup
     * @param pSuffix
     * @param <T>
     */
    private static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput pRecipeOutput,
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    // pid开始添加了MyMod.MOD_ID + ":" +
                    .save(pRecipeOutput, MyMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }
}
