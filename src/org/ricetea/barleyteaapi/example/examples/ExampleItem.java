package org.ricetea.barleyteaapi.example.examples;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.ricetea.barleyteaapi.api.item.data.DataItemRarity;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemCustomDurability;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemHoldEntityDamage;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemHoldEntityKill;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemHoldEntityAttack;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemHoldEntityDamagedByBlock;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemHoldEntityDamagedByEntity;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemHoldEntityDamagedByNothing;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemHoldEntityKillEntity;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemHoldEntityKillPlayer;
import org.ricetea.barleyteaapi.api.item.template.RegularItem;
import org.ricetea.barleyteaapi.util.Lazy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class ExampleItem extends RegularItem
        implements FeatureItemHoldEntityDamage, FeatureItemHoldEntityKill, FeatureItemCustomDurability {

    private static final Lazy<ExampleItem> inst = new Lazy<>(ExampleItem::new);

    @Nonnull
    public static ExampleItem getInstance() {
        return inst.get();
    }

    private ExampleItem() {
        //create a custom item that base on diamond sword and can give player with command "/givebarley <playerID> barleyteaapi_example:example_item"
        super(NamespacedKeyUtil.BarleyTeaAPIExample("example_item"), Material.DIAMOND_SWORD, DataItemRarity.EPIC);
    }

    @Override
    @Nonnull
    public String getDefaultName() { //set default item name
        return "Example Item";
    }

    @Override
    public boolean handleItemHoldEntityKillEntity(@Nonnull DataItemHoldEntityKillEntity data) {
        data.getHolderEntity()
                .sendMessage(
                        Component.text("you killed ").append(data.getDecedent().name()).append(Component.text("!")));
        return true;
    }

    @Override
    public boolean handleItemHoldEntityKillPlayer(@Nonnull DataItemHoldEntityKillPlayer data) {
        data.getHolderEntity()
                .sendMessage(
                        Component.text("you killed ").append(data.getDecedent().name()).append(Component.text("!")));
        return true;
    }

    @Override
    public boolean handleItemHoldEntityDamagedByEntity(@Nonnull DataItemHoldEntityDamagedByEntity data) {
        return true;
    }

    @Override
    public boolean handleItemHoldEntityDamagedByBlock(@Nonnull DataItemHoldEntityDamagedByBlock data) {
        return true;
    }

    @Override
    public boolean handleItemHoldEntityDamagedByNothing(@Nonnull DataItemHoldEntityDamagedByNothing data) {
        return true;
    }

    @Override
    public boolean handleItemHoldEntityAttack(@Nonnull DataItemHoldEntityAttack data) {
        data.getHolderEntity()
                .sendMessage(Component.text("you deal ", NamedTextColor.WHITE)
                        .append(Component.text(data.getFinalDamage(), NamedTextColor.GOLD))
                        .append(Component.text(" damage to ", NamedTextColor.WHITE))
                        .append(data.getDamagee().name())
                        .append(Component.text(" !", NamedTextColor.WHITE)));
        return true;
    }

    @Override
    public int getMaxDurability(@Nonnull ItemStack itemStack) {
        return 20;
    }

    @Override
    protected boolean handleItemGive(@Nonnull ItemStack itemStack) {
        return true;
    }
}
