package org.ricetea.barleyteaapi.example.utils;

import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public class NamespacedKeyUtil extends org.ricetea.barleyteaapi.util.NamespacedKeyUtil {

    public static final String Example = "example";

    protected NamespacedKeyUtil() {
    }

    @Nonnull
    public static NamespacedKey Example(@Nonnull String key) {
        return new NamespacedKey(Example, key);
    }

}
