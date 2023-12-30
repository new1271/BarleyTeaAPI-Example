package org.ricetea.barleyteaapi.example.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.ricetea.barleyteaapi.api.item.VanillaItemRarity;
import org.ricetea.barleyteaapi.api.item.feature.FeatureItemTick;
import org.ricetea.barleyteaapi.api.item.template.RegularItem;
import org.ricetea.barleyteaapi.example.utils.NamespacedKeyUtil;
import org.ricetea.utils.Lazy;

import javax.annotation.Nonnull;
import java.util.List;

/*
 * Speedster Boots
 *
 * when someone wear it on their feet, they will have Speed V potion effect.
 *
 * ID: example:speedster_boots
 * Durability: (just like normal leather boots)
 * Material (Base Item Type): Leather Boots
 * Can be enchanted/de-enchanted
 * Can be renamed/repaired
 * */

public final class SpeedsterBoots extends RegularItem implements FeatureItemTick {

    private static final int POTION_DURATION_MINIMUM = 200; //10sec
    private static final int POTION_DURATION_MAXIMUM = 300; //15sec
    private static final Lazy<SpeedsterBoots> _instLazy = Lazy.create(SpeedsterBoots::new);

    private SpeedsterBoots() {
        super(NamespacedKeyUtil.Example("speedster_boots"), Material.LEATHER_BOOTS, VanillaItemRarity.UNCOMMON);
    }

    @Nonnull
    public static SpeedsterBoots getInstance() {
        return _instLazy.get();
    }

    @Nonnull
    @Override
    public String getDefaultName() {
        return "Speedster Boots";
    }

    @Override
    protected boolean handleItemGive(@Nonnull ItemStack itemStack) {
        if (itemStack.getItemMeta() instanceof LeatherArmorMeta meta) {
            meta.lore(List.of(Component.text("SPEEED!").color(NamedTextColor.WHITE)));
            meta.setColor(Color.WHITE);
            itemStack.setItemMeta(meta);
            return true;
        }
        return false;
    }

    @Override
    public void handleTickOnEquipment(@Nonnull Player player, @Nonnull PlayerInventory inventory,
                                      @Nonnull ItemStack itemStack, @Nonnull EquipmentSlot slot) {
        if (slot == EquipmentSlot.FEET) {
            PotionEffect effect = player.getPotionEffect(PotionEffectType.SPEED);
            if (effect == null || (effect.getAmplifier() < 4 || effect.getDuration() < POTION_DURATION_MINIMUM)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4, POTION_DURATION_MAXIMUM));
            }
        }
    }

    @Override
    public void handleTickOnInventory(@Nonnull Player player, @Nonnull PlayerInventory inventory,
                                      @Nonnull ItemStack itemStack, int slot) {
        //Do nothing
    }
}
