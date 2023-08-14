package org.ricetea.barleyteaapi.example;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;

public final class NamespacedKeyUtil {
    public static @Nonnull NamespacedKey BarleyTeaAPIExample(String key) {
        return new NamespacedKey("barleyteaapi_example", key);
    }
}
