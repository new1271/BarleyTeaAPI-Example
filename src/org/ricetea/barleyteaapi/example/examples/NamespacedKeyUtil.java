package org.ricetea.barleyteaapi.example.examples;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;

final class NamespacedKeyUtil {
    public static @Nonnull String BarleyTeaAPIExample = "barleyteaapi_example";

    public static @Nonnull NamespacedKey BarleyTeaAPIExample(String key) {
        return new NamespacedKey(BarleyTeaAPIExample, key);
    }
}
