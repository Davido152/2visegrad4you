package net.davido152.toovisegradforyou.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties HOT_PEPPER = (new FoodProperties.Builder().nutrition(3).saturationMod(0.4f).build());
    public static final FoodProperties FULL_BEER_MUG = (new FoodProperties.Builder().nutrition(6).saturationMod(0.6f).effect(new MobEffectInstance(MobEffects.CONFUSION, 150), 1f).build());
}
