package org.ricetea.barleyteaapi.example;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.ricetea.barleyteaapi.api.entity.registration.EntityRegister;
import org.ricetea.barleyteaapi.api.item.data.DataItemType;
import org.ricetea.barleyteaapi.api.item.recipe.ShapedCraftingRecipe;
import org.ricetea.barleyteaapi.api.item.registration.CraftingRecipeRegister;
import org.ricetea.barleyteaapi.api.item.registration.ItemRegister;
import org.ricetea.barleyteaapi.example.items.SpeedsterBoots;
import org.ricetea.barleyteaapi.example.items.TankShield;
import org.ricetea.barleyteaapi.example.mobs.TankZombie;
import org.ricetea.barleyteaapi.example.utils.NamespacedKeyUtil;

import java.util.List;

public final class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin start logic

        // Register all items
        ItemRegister.getInstance().registerAll(List.of(
                SpeedsterBoots.getInstance(), TankShield.getInstance()
        ));
        // Register entity
        EntityRegister.getInstance().register(TankZombie.getInstance());
        // Register crafting recipe
        DataItemType A = DataItemType.get(Material.SUGAR);
        DataItemType B = DataItemType.get(Material.LEATHER_BOOTS);
        DataItemType resultType = DataItemType.get(SpeedsterBoots.getInstance());
        ShapedCraftingRecipe recipe = new ShapedCraftingRecipe(
                NamespacedKeyUtil.Example("crafting_speedster_boots"),
                new DataItemType[] {
                        A, A, A,
                        A, B, A,
                        A, A, A
                },
                3, //We have 3 columns in crafting matrix
                resultType
        );
        CraftingRecipeRegister.getInstance().register(recipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Unregister all items which the plugin registered
        ItemRegister.getInstance().unregisterAll(
                itemType -> itemType.getKey().getNamespace().equals(NamespacedKeyUtil.Example));

        // Unregister all entities which the plugin registered
        EntityRegister.getInstance().unregisterAll(
                itemType -> itemType.getKey().getNamespace().equals(NamespacedKeyUtil.Example));

        // Unregister all crafting recipes which the plugin registered
        CraftingRecipeRegister.getInstance().unregisterAll(
                itemType -> itemType.getKey().getNamespace().equals(NamespacedKeyUtil.Example));
    }
}
