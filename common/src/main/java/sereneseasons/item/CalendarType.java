/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.item;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.CrossbowItem;

public enum CalendarType implements StringRepresentable {
    STANDARD("standard"),
    TROPICAL("tropical"),
    NONE("none");

    public static final Codec<CalendarType> CODEC = StringRepresentable.fromEnum(CalendarType::values);
    private final String name;

    private CalendarType(String p_386626_) {
        this.name = p_386626_;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}