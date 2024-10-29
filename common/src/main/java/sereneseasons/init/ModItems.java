package sereneseasons.init;

import glitchcore.util.Environment;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import sereneseasons.api.SSBlocks;
import sereneseasons.api.SSItems;
import sereneseasons.core.SereneSeasons;
import sereneseasons.item.CalendarItem;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static sereneseasons.api.SSItems.*;

public class ModItems
{
    public static void setup(BiConsumer<ResourceLocation, Item> func)
    {
        registerItems(func);
        registerBlockItems(func);

        if (Environment.isClient())
        {
            ModClient.registerItemProperties();
        }
    }

    public static void registerItems(BiConsumer<ResourceLocation, Item> func)
    {
    	// SS Creative Tab Icon
        SSItems.SS_ICON = registerItem(func, "ss_icon", new Item.Properties());

        // Main Items
        SSItems.CALENDAR = registerItem(func, "calendar", CalendarItem::new, new Item.Properties().stacksTo(1));
    }

    public static void registerBlockItems(BiConsumer<ResourceLocation, Item> func)
    {
        SEASON_SENSOR = registerBlock(func, SSBlocks.SEASON_SENSOR);
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block)
    {
        return registerBlock(func, block, BlockItem::new);
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block, BiFunction<Block, Item.Properties, Item> factory)
    {
        return registerBlock(func, block, factory, new Item.Properties());
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block, BiFunction<Block, Item.Properties, Item> factory, Item.Properties properties)
    {
        return registerItem(func, blockIdToItemId(block.builtInRegistryHolder().key()), p_370785_ -> factory.apply(block, p_370785_), properties.useBlockDescriptionPrefix()
        );
    }

    private static Item registerItem(BiConsumer<ResourceLocation, Item> func, ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties)
    {
        Item item = factory.apply(properties.setId(key));
        func.accept(key.location(), item);
        return item;
    }

    private static Item registerItem(BiConsumer<ResourceLocation, Item> func, String name, Function<Item.Properties, Item> factory, Item.Properties properties)
    {
        return registerItem(func, itemId(name), factory, properties);
    }

    private static Item registerItem(BiConsumer<ResourceLocation, Item> func, String name, Item.Properties properties)
    {
        return registerItem(func, itemId(name), Item::new, properties);
    }

    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> key)
    {
        return ResourceKey.create(Registries.ITEM, key.location());
    }

    private static ResourceKey<Item> itemId(String name)
    {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SereneSeasons.MOD_ID, name));
    }
}
