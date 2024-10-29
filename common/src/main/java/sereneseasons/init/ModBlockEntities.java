/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.init;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import sereneseasons.api.SSBlockEntities;
import sereneseasons.api.SSBlocks;
import sereneseasons.block.entity.SeasonSensorBlockEntity;
import sereneseasons.core.SereneSeasons;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModBlockEntities
{
    public static void registerBlockEntities(BiConsumer<ResourceLocation, BlockEntityType<?>> func)
    {
        SSBlockEntities.SEASON_SENSOR = register(func, "season_sensor", SeasonSensorBlockEntity::new, Set.of(SSBlocks.SEASON_SENSOR));
    }

    private static <T extends BlockEntity> BlockEntityType<?> register(BiConsumer<ResourceLocation, BlockEntityType<?>> func, String name, BlockEntityType.BlockEntitySupplier<T> supplier, Set<Block> blocks)
    {
        var type = new BlockEntityType(supplier, blocks);
        func.accept(ResourceLocation.fromNamespaceAndPath(SereneSeasons.MOD_ID, name), type);
        return type;
    }
}
