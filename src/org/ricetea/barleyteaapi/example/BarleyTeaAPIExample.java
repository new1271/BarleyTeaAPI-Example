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
        ItemRegister.getInstance().register(ExampleItem.getInstance());
        logger.info("registering examples successful!");
    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();
        logger.info("unregistering examples...");
        EntityRegister.getInstance().unregister(ExampleEntity.getInstance());
        ItemRegister.getInstance().unregister(ExampleItem.getInstance());
        logger.info("unregistering examples successful!");
    }
}
