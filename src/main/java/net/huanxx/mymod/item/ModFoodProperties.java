package net.huanxx.mymod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    /**
     * <p>
     *     这是一个食物的属性，可以在创建物品时用于属性插槽
     * </p>
     */
     public static final FoodProperties SPECIAL_FOOD = new FoodProperties.Builder().nutrition(3).saturationModifier(.25f).effect(new MobEffectInstance(MobEffects.INVISIBILITY, 400), .8f).build();
}
