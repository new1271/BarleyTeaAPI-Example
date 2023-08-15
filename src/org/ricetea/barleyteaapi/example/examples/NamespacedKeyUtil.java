package org.ricetea.barleyteaapi.example.examples;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;

final class NamespacedKeyUtil {
    public static @Nonnull NamespacedKey BarleyTeaAPIExample(String key) {
        return new NamespacedKey("barleyteaapi_example", key);
    }
}
