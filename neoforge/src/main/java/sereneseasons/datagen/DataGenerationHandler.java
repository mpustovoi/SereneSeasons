/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import sereneseasons.datagen.provider.SSRecipeProvider;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGenerationHandler
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Client
        generator.addProvider(event.includeClient(), new SSBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new SSItemModelProvider(output, existingFileHelper));

        // Recipes
        generator.addProvider(event.includeServer(), new SSRecipeProvider.Runner(output, event.getLookupProvider()));
    }
}
