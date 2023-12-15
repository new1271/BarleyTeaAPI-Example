package org.ricetea.barleyteaapi.example.examples;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.ricetea.barleyteaapi.api.entity.feature.FeatureEntityDamage;
import org.ricetea.barleyteaapi.api.entity.feature.data.DataEntityAttack;
import org.ricetea.barleyteaapi.api.entity.feature.data.DataEntityDamagedByBlock;
import org.ricetea.barleyteaapi.api.entity.feature.data.DataEntityDamagedByEntity;
import org.ricetea.barleyteaapi.api.entity.feature.data.DataEntityDamagedByNothing;
import org.ricetea.barleyteaapi.api.entity.helper.EntityHelper;
import org.ricetea.barleyteaapi.api.entity.template.SpawnableEntity;
import org.ricetea.utils.Lazy;

public class ExampleEntity2 extends SpawnableEntity implements FeatureEntityDamage {
    private static final Lazy<ExampleEntity2> inst = Lazy.create(ExampleEntity2::new);

    @Nonnull
    public static ExampleEntity2 getInstance() {
        return inst.get();
    }

    private ExampleEntity2() {
        super(NamespacedKeyUtil.BarleyTeaAPIExample("example_entity_2"), EntityType.VILLAGER);
    }

    @Override
    @Nonnull
    public String getDefaultName() { //set default display name for /summonbarley command's result in chat bar
        return "Example Entity 2";
    }

    @Override
    public boolean handleEntityAttack(@Nonnull DataEntityAttack arg0) {
        return true;
    }

    @Override
    public boolean handleEntityDamagedByBlock(@Nonnull DataEntityDamagedByBlock arg0) {
        return true;
    }

    @Override
    public boolean handleEntityDamagedByEntity(@Nonnull DataEntityDamagedByEntity arg0) {
        EntityHelper.damage(arg0.getDamager(), arg0.getEntity(), DamageCause.FREEZE,
                (float) arg0.getFinalDamage());
        return true;
    }

    @Override
    public boolean handleEntityDamagedByNothing(@Nonnull DataEntityDamagedByNothing arg0) {
        return true;
    }

    @Override
    protected boolean handleEntitySpawn(@Nonnull Entity entitySummoned) {
        setEntityName(entitySummoned, ChatColor.BLUE, ChatColor.BOLD);
        return true;
    }

}
