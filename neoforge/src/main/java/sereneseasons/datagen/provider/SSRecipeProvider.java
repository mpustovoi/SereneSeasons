/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import sereneseasons.api.SSBlocks;
import sereneseasons.api.SSItems;

import java.util.concurrent.CompletableFuture;

public class SSRecipeProvider extends RecipeProvider
{
    public SSRecipeProvider(HolderLookup.Provider provider, RecipeOutput output)
    {
        super(provider, output);
    }

    @Override
    protected void buildRecipes()
    {
        this.shaped(RecipeCategory.REDSTONE, SSBlocks.SEASON_SENSOR).define('G', Items.GLASS).define('Q', Items.QUARTZ).define('C', SSItems.CALENDAR).define('#', Blocks.COBBLESTONE_SLAB).pattern("GGG").pattern("QCQ").pattern("###").unlockedBy("has_calendar", has(SSItems.CALENDAR)).save(output);
        this.shaped(RecipeCategory.TOOLS, SSItems.CALENDAR).define('P', Items.PAPER).define('C', Items.CLOCK).pattern("PPP").pattern("PCP").pattern("PPP").unlockedBy("has_clock", has(Items.CLOCK)).save(output);
    }

    public static class Runner extends RecipeProvider.Runner
    {
        public Runner(PackOutput p_365442_, CompletableFuture<HolderLookup.Provider> p_362168_) {
            super(p_365442_, p_362168_);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output)
        {
            return new SSRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "SS Recipes";
        }
    }
}
