package org.ricetea.barleyteaapi.example.examples;

import org.bukkit.Material;
import org.ricetea.barleyteaapi.api.item.data.DataItemType;
import org.ricetea.barleyteaapi.api.item.recipe.BaseCraftingRecipe;
import org.ricetea.barleyteaapi.api.item.recipe.ShapedCraftingRecipe;
import org.ricetea.barleyteaapi.api.item.recipe.ShapelessCraftingRecipe;
import org.ricetea.barleyteaapi.api.item.registration.CraftingRecipeRegister;

public final class ExampleRecipes {
    private static BaseCraftingRecipe craftingRecipe, craftingRecipe2, craftingRecipe3;

    public static void registerRecipes() {
        try {
            DataItemType A = ExampleItem.getInstance().getType();
            DataItemType B = ExampleItem2.getInstance().getType();
            DataItemType C = DataItemType.create(Material.DIAMOND_BLOCK);
            DataItemType D = DataItemType.create(Material.DIRT);
            DataItemType E = DataItemType.create(Material.DIAMOND);
            ShapedCraftingRecipe craftingRecipe = new ShapedCraftingRecipe(
                    NamespacedKeyUtil.BarleyTeaAPIExample("example_recipe"), new DataItemType[] {
                            A, A,
                            A, A,
                    }, 2, B);

            ShapedCraftingRecipe craftingRecipe2 = new ShapedCraftingRecipe(
                    NamespacedKeyUtil.BarleyTeaAPIExample("example_recipe2"), new DataItemType[] {
                            C, C, C,
                            C, B, C,
                            C, C, C,
                    }, 3, D);
            ShapelessCraftingRecipe craftingRecipe3 = new ShapelessCraftingRecipe(
                    NamespacedKeyUtil.BarleyTeaAPIExample("example_recipe3"), new DataItemType[] {
                            E, E, E,
                    }, A);
            CraftingRecipeRegister.getInstance().register(craftingRecipe);
            CraftingRecipeRegister.getInstance().register(craftingRecipe2);
            CraftingRecipeRegister.getInstance().register(craftingRecipe3);
            ExampleRecipes.craftingRecipe = craftingRecipe;
            ExampleRecipes.craftingRecipe2 = craftingRecipe2;
            ExampleRecipes.craftingRecipe3 = craftingRecipe3;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterRecipes() {
        try {
            BaseCraftingRecipe craftingRecipe = ExampleRecipes.craftingRecipe;
            if (craftingRecipe != null)
                CraftingRecipeRegister.getInstance().unregister(craftingRecipe);
            BaseCraftingRecipe craftingRecipe2 = ExampleRecipes.craftingRecipe2;
            if (craftingRecipe2 != null)
                CraftingRecipeRegister.getInstance().unregister(craftingRecipe2);
            BaseCraftingRecipe craftingRecipe3 = ExampleRecipes.craftingRecipe3;
            if (craftingRecipe3 != null)
                CraftingRecipeRegister.getInstance().unregister(craftingRecipe3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
