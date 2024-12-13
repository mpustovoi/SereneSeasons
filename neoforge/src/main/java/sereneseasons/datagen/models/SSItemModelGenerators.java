/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.Time;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import sereneseasons.api.SSItems;
import sereneseasons.client.item.ContextCalendarType;
import sereneseasons.client.item.SeasonTimeProperty;
import sereneseasons.item.CalendarType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;

public class SSItemModelGenerators extends ItemModelGenerators
{
    private final ItemModelOutput itemModelOutput;
    private final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public SSItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
        this.itemModelOutput = itemModelOutput;
        this.modelOutput = modelOutput;
    }

    public void generateCalendarItem(Item item) {
        // Null calendar
        ItemModel.Unbaked nullCalendar = ItemModelUtils.plainModel(this.createFlatItemModel(item, "_null", ModelTemplates.FLAT_ITEM));

        List<RangeSelectItemModel.Entry> standardEntries = new ArrayList<>();
        List<RangeSelectItemModel.Entry> tropicalEntries = new ArrayList<>();

        ItemModel.Unbaked[] standardModels = new ItemModel.Unbaked[12];
        ItemModel.Unbaked[] tropicalModels = new ItemModel.Unbaked[6];


        // Create standard entries and tropical models
        for (int i = 0; i < 12; i++)
        {
            standardModels[i] = ItemModelUtils.plainModel(this.createFlatItemModel(item, String.format("_%02d", i), ModelTemplates.FLAT_ITEM));
            standardEntries.add(ItemModelUtils.override(standardModels[i], i / 12.0F));
            if (i < 6) tropicalModels[i] = ItemModelUtils.plainModel(this.createFlatItemModel(item, String.format("_tropical_%02d", i), ModelTemplates.FLAT_ITEM));
        }

        // Create tropical overrides
        for (int i = 0; i < 12; i++)
        {
            tropicalEntries.add(ItemModelUtils.override(tropicalModels[((i + 3) / 2) % 6], i / 12.0F));
        }

        this.itemModelOutput
                .accept(
                        item,
                        ItemModelUtils.select(new ContextCalendarType(),
                                ItemModelUtils.when(CalendarType.STANDARD, ItemModelUtils.rangeSelect(new SeasonTimeProperty(), 1.0F, standardEntries)),
                                ItemModelUtils.when(CalendarType.TROPICAL, ItemModelUtils.rangeSelect(new SeasonTimeProperty(), 1.0F, tropicalEntries)),
                                ItemModelUtils.when(CalendarType.NONE, nullCalendar))
                );
    }

    @Override
    public void run()
    {
        this.generateFlatItem(SSItems.SS_ICON, ModelTemplates.FLAT_ITEM);
        this.generateCalendarItem(SSItems.CALENDAR);
    }
}
