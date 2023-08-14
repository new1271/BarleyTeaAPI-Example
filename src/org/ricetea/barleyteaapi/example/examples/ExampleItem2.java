package org.ricetea.barleyteaapi.example.examples;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.ricetea.barleyteaapi.api.item.data.DataItemRarity;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemCustomDurability;
import org.ricetea.barleyteaapi.api.item.template.RegularItem;
import org.ricetea.barleyteaapi.example.NamespacedKeyUtil;
import org.ricetea.barleyteaapi.util.Lazy;

public final class ExampleItem2 extends RegularItem
        implements FeatureItemCustomDurability {

    private static final Lazy<ExampleItem2> inst = new Lazy<>(ExampleItem2::new);

    @Nonnull
    public static ExampleItem2 getInstance() {
        return inst.get();
    }

    private ExampleItem2() {
        //create a custom item that base on wooden sword and can give player with command "/givebarley <playerID> barleyteaapi_example:example_item_2"
        super(NamespacedKeyUtil.BarleyTeaAPIExample("example_item_2"), Material.WOODEN_SWORD, DataItemRarity.RARE);
    }

    @Override
    @Nonnull
    public String getDefaultName() { //set default item name
        return "Example Item 2";
    }

    @Override
    public int getMaxDurability(@Nonnull ItemStack itemStack) {
        return 100000;
    }

    @Override
    protected boolean handleItemGive(ItemStack itemStack) {
        setDefaultAttackDamage(itemStack, 15);
        setDefaultAttackSpeed(itemStack, 0.75);
        return true;
    }
}