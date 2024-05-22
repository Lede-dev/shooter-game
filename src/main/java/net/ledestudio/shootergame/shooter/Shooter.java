package net.ledestudio.shootergame.shooter;

import net.ledestudio.shootergame.projectile.ProjectileFactory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Shooter {

    @NotNull Player getBukkitPlayer();

    void setup(@NotNull ProjectileFactory projectileFactory);

    void clear();

    boolean hasRemainProjectile();

}
