package net.ledestudio.shootergame.shooter;

import net.ledestudio.shootergame.projectile.ProjectileFactory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArrowShooter implements Shooter {

    private final Player player;

    public ArrowShooter(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull Player getBukkitPlayer() {
        return player;
    }

    @Override
    public void setup(@NotNull ProjectileFactory arrowFactory) {

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean hasRemainProjectile() {
        return false;
    }

}
