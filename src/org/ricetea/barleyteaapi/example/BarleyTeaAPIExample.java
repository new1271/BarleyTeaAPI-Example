package org.ricetea.barleyteaapi.example;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.ricetea.barleyteaapi.api.entity.registration.EntityRegister;
import org.ricetea.barleyteaapi.api.item.registration.ItemRegister;
import org.ricetea.barleyteaapi.example.examples.*;

public final class BarleyTeaAPIExample extends JavaPlugin {
    @Override
    public void onEnable() {
        Logger logger = getLogger();
        logger.info("registering examples...");
        EntityRegister.getInstance().register(ExampleEntity.getInstance());
        EntityRegister.getInstance().register(ExampleEntity2.getInstance());
        ItemRegister.getInstance().register(ExampleItem.getInstance());
        ItemRegister.getInstance().register(ExampleItem2.getInstance());
        ExampleRecipes.registerRecipes();
        logger.info("registering examples successful!");
    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();
        logger.info("unregistering examples...");
        EntityRegister.getInstance().unregister(ExampleEntity.getInstance());
        EntityRegister.getInstance().unregister(ExampleEntity2.getInstance());
        ItemRegister.getInstance().unregister(ExampleItem.getInstance());
        ItemRegister.getInstance().unregister(ExampleItem2.getInstance());
        ExampleRecipes.unregisterRecipes();
        logger.info("unregistering examples successful!");
    }
}
