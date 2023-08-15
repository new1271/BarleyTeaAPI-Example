package org.ricetea.barleyteaapi.example.examples;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.ricetea.barleyteaapi.api.entity.feature.*;
import org.ricetea.barleyteaapi.api.entity.feature.data.*;
import org.ricetea.barleyteaapi.api.entity.helper.EntityHelper;
import org.ricetea.barleyteaapi.api.entity.template.SpawnableEntity;
import org.ricetea.barleyteaapi.util.Lazy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public final class ExampleEntity extends SpawnableEntity // based on BarleyTeaAPI's Spawnable Entity Class
        implements FeatureEntityDamage, FeatureEntityDeath, FeatureKillEntity { // implements entity features

    private static final Lazy<ExampleEntity> inst = new Lazy<>(ExampleEntity::new);

    @Nonnull
    public static ExampleEntity getInstance() {
        return inst.get();
    }

    private ExampleEntity() {
        //create a custom entity that base on zombie and can summon with command "/summonbarley barleyteaapi_example:example_entity"
        super(NamespacedKeyUtil.BarleyTeaAPIExample("example_entity"), EntityType.ZOMBIE);
    }

    @Override
    @Nonnull
    public String getDefaultName() { //set default display name for /summonbarley command's result in chat bar
        return "Example Entity";
    }

    @Override
    public boolean handleEntityDeath(@Nonnull DataEntityDeath data) {
        if (data.hasKiller()) {
            Entity killer = data.getKiller();
            if (killer instanceof Player) //if killer is player
                killer.sendMessage(data.getEntity().name().append(Component.text(" is dead!"))
                        .style(Style.style(NamedTextColor.GOLD)));
            else if (killer instanceof Projectile) { //if killer is arrow, fireball or something that is projectile
                killer = EntityHelper.getProjectileShooterEntity((Projectile) killer);
                if (killer != null && killer instanceof Player) //if killer is exist and killer is player
                    killer.sendMessage(data.getEntity().name()
                            .append(Component.text(" is dead!").style(Style.style(NamedTextColor.GOLD))));
            }
        }
        return true; //accept decedent(the entity) deaths
    }

    @Override
    public boolean handleEntityDamagedByEntity(@Nonnull DataEntityDamagedByEntity data) {
        if (data.getDamager() instanceof Player) {
            data.getDamager().sendMessage(data.getEntity().name()
                    .append(Component.text(" is dealed ").style(Style.style(NamedTextColor.WHITE)))
                    .append(Component.text(data.getDamage()).style(Style.style(NamedTextColor.GOLD)))
                    .append(Component.text(" damage!")).style(Style.style(NamedTextColor.WHITE)));
        }
        return true; //accept the entity is damaged by another entity
    }

    @Override
    public boolean handleEntityDamagedByBlock(@Nonnull DataEntityDamagedByBlock data) {
        return true; //accept the entity is damaged by block
    }

    @Override
    public boolean handleEntityDamagedByNothing(@Nonnull DataEntityDamagedByNothing data) {
        return true; //accept the entity is damaged by nothing(environment)
    }

    @Override
    public boolean handleEntityAttack(@Nonnull DataEntityAttack data) {
        if (data.getDamagee() instanceof Player) {
            data.getDamagee().sendMessage(data.getEntity().name()
                    .append(Component.text(" damages you ").style(Style.style(NamedTextColor.WHITE)))
                    .append(Component.text(data.getFinalDamage()).style(Style.style(NamedTextColor.GOLD)))
                    .append(Component.text(" !")).style(Style.style(NamedTextColor.WHITE)));
        }
        return true; //accept the entity attacks another entity
    }

    @SuppressWarnings("deprecation")
    @Override
    protected boolean handleEntitySpawn(@Nonnull Entity entitySummoned) {
        //Named entity as "default"(the name that is getDefaultName() set), and set color&style as ChatColor.BLUE + ChatColor.BOLD
        setEntityName(entitySummoned, ChatColor.BLUE, ChatColor.BOLD);
        return true;
    }

    @Override
    public boolean handleKillEntity(@Nonnull DataKillEntity data) {
        return true; //accept the decedent death
    }

    @Override
    public boolean handleKillPlayer(@Nonnull DataKillPlayer data) {
        Player decedent = data.getDecedent();
        decedent.sendMessage(Component.text("You're killed by ").style(Style.style(NamedTextColor.WHITE))
                .append(data.getEntity().name())
                .append(Component.text(" !").style(Style.style(NamedTextColor.WHITE))));
        return true; //accept the decedent death
    }
}
