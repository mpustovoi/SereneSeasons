/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import sereneseasons.api.SSBlocks;
import sereneseasons.api.season.Season;
import sereneseasons.block.SeasonSensorBlock;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SSBlockModelGenerators extends BlockModelGenerators
{
    final Consumer<BlockStateGenerator> blockStateOutput;
    final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public SSBlockModelGenerators(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput)
    {
        super(blockStateOutput, itemModelOutput, modelOutput);
        this.blockStateOutput = blockStateOutput;
        this.modelOutput = modelOutput;
    }

    private void createSeasonSensor()
    {
        ResourceLocation sideTexture = TextureMapping.getBlockTexture(SSBlocks.SEASON_SENSOR, "_side");
        TextureMapping textures = new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(SSBlocks.SEASON_SENSOR, "_summer_top"))
                .put(TextureSlot.SIDE, sideTexture);
        TextureMapping autumnTextures = new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(SSBlocks.SEASON_SENSOR, "_autumn_top"))
                .put(TextureSlot.SIDE, sideTexture);
        TextureMapping winterTextures = new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(SSBlocks.SEASON_SENSOR, "_winter_top"))
                .put(TextureSlot.SIDE, sideTexture);
        TextureMapping springTextures = new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(SSBlocks.SEASON_SENSOR, "_spring_top"))
                .put(TextureSlot.SIDE, sideTexture);

        this.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(SSBlocks.SEASON_SENSOR).with(
                PropertyDispatch.property(SeasonSensorBlock.SEASON)
                    .select(
                        Season.SUMMER.ordinal(),
                        Variant.variant().with(VariantProperties.MODEL, ModelTemplates.DAYLIGHT_DETECTOR.createWithSuffix(SSBlocks.SEASON_SENSOR, "_summer", textures, this.modelOutput))
                    )
                    .select(
                        Season.AUTUMN.ordinal(),
                        Variant.variant().with(VariantProperties.MODEL, ModelTemplates.DAYLIGHT_DETECTOR.createWithSuffix(SSBlocks.SEASON_SENSOR, "_autumn", autumnTextures, this.modelOutput))
                    )
                    .select(
                        Season.WINTER.ordinal(),
                        Variant.variant().with(VariantProperties.MODEL, ModelTemplates.DAYLIGHT_DETECTOR.createWithSuffix(SSBlocks.SEASON_SENSOR, "_winter", winterTextures, this.modelOutput))
                    )
                    .select(
                        Season.SPRING.ordinal(),
                        Variant.variant().with(VariantProperties.MODEL, ModelTemplates.DAYLIGHT_DETECTOR.create(SSBlocks.SEASON_SENSOR, springTextures, this.modelOutput))
                    )
            )
        );
    }

    @Override
    public void run()
    {
        this.createSeasonSensor();
    }
}
