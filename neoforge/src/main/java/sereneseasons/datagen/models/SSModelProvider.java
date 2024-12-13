/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen.models;

import glitchcore.data.ModelProviderBase;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import sereneseasons.core.SereneSeasons;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SSModelProvider extends ModelProviderBase
{
    public SSModelProvider(PackOutput output)
    {
        super(output, SereneSeasons.MOD_ID);
    }

    @Override
    protected BlockModelGenerators createBlockModelGenerators(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput)
    {
        return new SSBlockModelGenerators(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    protected ItemModelGenerators createItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput)
    {
        return new SSItemModelGenerators(itemModelOutput, modelOutput);
    }
}
