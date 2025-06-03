package org.ricetea.barleyteaapi.example.items;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.ricetea.barleyteaapi.api.item.VanillaItemRarity;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemAnvil;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemCustomDurability;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemEnchant;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemAnvilCombine;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemAnvilRename;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemAnvilRepair;
import org.ricetea.barleyteaapi.api.item.feature.data.DataItemEnchant;
import org.ricetea.barleyteaapi.api.item.helper.ItemHelper;
import org.ricetea.barleyteaapi.api.item.template.RegularItem;
import org.ricetea.barleyteaapi.example.utils.NamespacedKeyUtil;

import javax.annotation.Nonnull;

/*
 * Tank Shield
 *
 * when someone wear it on their off-hand, they will have 10 Armor Points and -10% Speed
 *
 * ID: example:tank_shield
 * Durability: 1000
 * Material (Base Item Type): Shield
 * Can not be enchanted/de-enchanted
 * Can be renamed/repaired
 * */

public final class TankShield extends RegularItem implements FeatureItemAnvil, FeatureItemEnchant, FeatureItemCustomDurability {

    private static final TankShield _instance = new TankShield();

    private TankShield() {
        super(NamespacedKeyUtil.Example("tank_shield"), Material.SHIELD, VanillaItemRarity.EPIC);
    }

    @Nonnull
    public static TankShield getInstance() {
        return _instance;
    }

    @Nonnull
    @Override
    public String getDefaultName() {
        return "Tank Shield";
    }

    @Override
    protected boolean handleItemGive(@Nonnull ItemStack itemStack) {
        ItemHelper.setDefaultAttribute(itemStack, Attribute.GENERIC_ARMOR, 10,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        ItemHelper.setDefaultAttribute(itemStack, Attribute.GENERIC_MOVEMENT_SPEED, -0.1,
                AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.OFF_HAND);
        return true;
    }

    @Override
    public int getMaxDurability(@Nonnull ItemStack itemStack) {
        return 1000;
    }

    @Override
    public boolean handleItemAnvilRename(@Nonnull DataItemAnvilRename data) {
        return true;
    }

    @Override
    public boolean handleItemAnvilCombine(@Nonnull DataItemAnvilCombine data) {
        return !data.getItemStackCombinedType()
                .nonNullMap(
                        material -> material.equals(Material.ENCHANTED_BOOK),
                        ignored -> false);
    }

    @Override
    public boolean handleItemAnvilRepair(@Nonnull DataItemAnvilRepair data) {
        return true;
    }

    @Override
    public boolean handleItemEnchant(@Nonnull DataItemEnchant data) {
        return false;
    }

}
