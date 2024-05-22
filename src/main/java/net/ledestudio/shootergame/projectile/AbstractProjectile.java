package net.ledestudio.shootergame.projectile;

import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractProjectile {

    protected final @NotNull ItemStack item;

    public AbstractProjectile(@NotNull ItemStack item) {
        this.item = item;
    }

    public final @NotNull ItemStack getProjectileItemStack() {
        return item.clone();
    }

    public abstract void onProjectileHit(@NotNull Shooter shooter, @NotNull ProjectileTarget target, @NotNull Block block, @NotNull BlockFace hitFace);

}
