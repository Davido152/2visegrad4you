package net.davido152.toovisegradforyou.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties HOT_PEPPER = (new FoodProperties.Builder().nutrition(3).saturationMod(0.4f).build());
    public static final FoodProperties FULL_BEER_MUG = (new FoodProperties.Builder().nutrition(6).saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 150), 1f).build());
    public static final FoodProperties FULL_BEER_CAN = (new FoodProperties.Builder().nutrition(5).saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 150), 1f).build());
    public static final FoodProperties SCHNITZEL = (new FoodProperties.Builder().nutrition(10).saturationMod(0.7f).meat().build());
    public static final FoodProperties RAW_SCHNITZEL = (new FoodProperties.Builder().nutrition(4).saturationMod(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400),0.8f).meat().build());
    public static final FoodProperties BUTTER = (new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().build());

}
