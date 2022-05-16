package gregification.forestry.recipes;

import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.items.EnumPropolis;
import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import gregification.base.BaseUtility;
import gregification.base.ModIDs;
import gregification.forestry.ForestryModule;
import gregification.forestry.ForestryUtils;
import gregification.forestry.bees.GTDropType;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class MiscRecipes {

    public static void registerHandlers() {
        OrePrefix.stick.addProcessingHandler(PropertyKey.TOOL, MiscRecipes::processScoop);
    }

    public static void init() {

        // Extra Bees Propolis
        if (Loader.isModLoaded(ModIDs.MODID_EB)) {
            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                    .inputs(BaseUtility.getModItem(ModIDs.MODID_EB, "propolis", 0))
                    .fluidOutputs(Materials.Water.getFluid(500))
                    .duration(32).EUt(7).buildAndRegister();

            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                    .inputs(BaseUtility.getModItem(ModIDs.MODID_EB, "propolis", 1))
                    .fluidOutputs(Materials.Oil.getFluid(500))
                    .duration(32).EUt(7).buildAndRegister();

            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                    .inputs(BaseUtility.getModItem(ModIDs.MODID_EB, "propolis", 7))
                    .fluidOutputs(Materials.Creosote.getFluid(500))
                    .duration(32).EUt(7).buildAndRegister();
        }

        // Oil Drop
        ItemStack dropStack = ForestryUtils.getDropStack(GTDropType.OIL);
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(dropStack)
                .chancedOutput(ModuleApiculture.getItems().propolis.get(EnumPropolis.NORMAL, 1), 3000, 0)
                .fluidOutputs(Materials.OilHeavy.getFluid(100))
                .duration(32).EUt(8).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.SQUEEZER)) {
            RecipeManagers.squeezerManager.addRecipe(40, dropStack, Materials.OilHeavy.getFluid(100), ModuleApiculture.getItems().propolis.get(EnumPropolis.NORMAL, 1), 30);
        }

        // Biomass Drop
        dropStack = ForestryUtils.getDropStack(GTDropType.BIOMASS);
        ItemStack propolisStack = ModuleApiculture.getItems().propolis.get(EnumPropolis.NORMAL, 1);
        if (Loader.isModLoaded(ModIDs.MODID_EB)) {
            propolisStack = BaseUtility.getModItem(ModIDs.MODID_EB, "propolis", 7);
        }
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(dropStack)
                .chancedOutput(propolisStack, 3000, 0)
                .fluidOutputs(Materials.Biomass.getFluid(100))
                .duration(32).EUt(8).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.SQUEEZER)) {
            RecipeManagers.squeezerManager.addRecipe(40, dropStack, Materials.Biomass.getFluid(100), propolisStack, 30);
        }

        // Ethanol Drop
        dropStack = ForestryUtils.getDropStack(GTDropType.ETHANOL);
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(dropStack)
                .chancedOutput(propolisStack, 3000, 0)
                .fluidOutputs(Materials.Ethanol.getFluid(100))
                .duration(32).EUt(8).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.SQUEEZER)) {
            RecipeManagers.squeezerManager.addRecipe(40, dropStack, Materials.Ethanol.getFluid(100), propolisStack, 30);
        }

        // TODO Other drops

        // Honey and Juice recipes
        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(MetaItems.PLANT_BALL)
                .fluidInputs(ForestryModule.Honey.getFluid(180))
                .fluidOutputs(Materials.Biomass.getFluid(270))
                .duration(1440).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input("treeSapling", 1)
                .fluidInputs(ForestryModule.Honey.getFluid(100))
                .fluidOutputs(Materials.Biomass.getFluid(150))
                .duration(600).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.POTATO)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.CARROT)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Blocks.CACTUS)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.REEDS)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Blocks.BROWN_MUSHROOM)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Blocks.RED_MUSHROOM)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.BEETROOT)
                .fluidInputs(ForestryModule.Honey.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(MetaItems.PLANT_BALL)
                .fluidInputs(ForestryModule.Juice.getFluid(180))
                .fluidOutputs(Materials.Biomass.getFluid(270))
                .duration(1440).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input("treeSapling", 1)
                .fluidInputs(ForestryModule.Juice.getFluid(100))
                .fluidOutputs(Materials.Biomass.getFluid(150))
                .duration(600).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.POTATO)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.CARROT)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Blocks.CACTUS)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.REEDS)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Blocks.BROWN_MUSHROOM)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Blocks.RED_MUSHROOM)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder()
                .input(Items.BEETROOT)
                .fluidInputs(ForestryModule.Juice.getFluid(20))
                .fluidOutputs(Materials.Biomass.getFluid(30))
                .duration(160).EUt(3).buildAndRegister();
    }

    private static void processScoop(OrePrefix prefix, Material material, ToolProperty property) {
        ModHandler.addShapedRecipe(String.format("scoop_%s", material), ForestryModule.SCOOP.getStackForm(material),
                "SWS", "SSS", "xSh",
                'S', new UnificationEntry(OrePrefix.stick, material),
                'W', new ItemStack(Blocks.WOOL, 1, GTValues.W));
    }
}
