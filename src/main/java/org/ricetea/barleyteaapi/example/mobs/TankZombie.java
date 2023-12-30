package org.ricetea.barleyteaapi.example.mobs;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.ricetea.barleyteaapi.api.entity.feature.FeatureEntityDeath;
import org.ricetea.barleyteaapi.api.entity.feature.data.DataEntityDeath;
import org.ricetea.barleyteaapi.api.entity.helper.EntityHelper;
import org.ricetea.barleyteaapi.api.entity.template.SpawnableEntity;
import org.ricetea.barleyteaapi.example.items.TankShield;
import org.ricetea.barleyteaapi.example.utils.NamespacedKeyUtil;
import org.ricetea.utils.Lazy;

import javax.annotation.Nonnull;

/*
 * Tank Zombie
 *
 * very tough
 * says "Killed a tank" to the killer when killed by player
 *
 * ID: example:tank_zombie
 * Health: 100 (50 Hearts)
 * Base Entity Type: Zombie
 * */

public final class TankZombie extends SpawnableEntity implements FeatureEntityDeath {

    private static final Lazy<TankZombie> _instLazy = Lazy.create(TankZombie::new);

    private TankZombie() {
        super(NamespacedKeyUtil.Example("tank_zombie"), EntityType.ZOMBIE);
    }

    @Nonnull
    public static TankZombie getInstance(){
        return _instLazy.get();
    }

    @Nonnull
    @Override
    public String getDefaultName() {
        return "Tank Zombie";
    }

    @Override
    protected boolean handleEntitySpawn(@Nonnull Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            EntityEquipment equipment = livingEntity.getEquipment();
            if (equipment == null)
                return false;
            EntityHelper.setAttribute(entity, Attribute.GENERIC_MAX_HEALTH, 100.0);
            equipment.setItemInOffHand(TankShield.getInstance().handleItemGive(1), true);
            equipment.setHelmet(new ItemStack(Material.IRON_HELMET), true);
            equipment.setChestplate(new ItemStack(Material.IRON_CHESTPLATE), true);
            equipment.setLeggings(new ItemStack(Material.IRON_LEGGINGS), true);
            equipment.setBoots(new ItemStack(Material.IRON_BOOTS), true);
            EntityHelper.setEntityName(this, livingEntity);
            EntityHelper.setAsMaxHealth(livingEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean handleEntityDeath(@Nonnull DataEntityDeath data) {
        if (data.getKiller() instanceof Player player) {
            player.sendMessage(Component.text("Killed a tank!"));
        }
        return true;
    }

}
