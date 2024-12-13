/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.client.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import sereneseasons.api.season.SeasonHelper;
import sereneseasons.season.SeasonTime;

public class SeasonTimeProperty implements RangeSelectItemModelProperty
{
    public static final MapCodec<SeasonTimeProperty> TYPE = MapCodec.unit(new SeasonTimeProperty());

    @Override
    public float get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity livingEntity, int i)
    {
        Entity holder = (Entity)(livingEntity != null ? livingEntity : itemStack.getFrame());

        if (level == null && holder != null) level = (ClientLevel)holder.level();
        if (level == null) return 0.0F;

        double d0;

        int seasonCycleTicks = SeasonHelper.getSeasonState(level).getSeasonCycleTicks();
        d0 = (float)seasonCycleTicks / (float) SeasonTime.ZERO.getCycleDuration();
        return Mth.positiveModulo((float)d0, 1.0F);
    }

    @Override
    public MapCodec<? extends RangeSelectItemModelProperty> type()
    {
        return TYPE;
    }
}
