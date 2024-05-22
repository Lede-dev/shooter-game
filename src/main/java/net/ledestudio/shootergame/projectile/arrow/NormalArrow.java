package net.ledestudio.shootergame.projectile.arrow;

import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NormalArrow extends AbstractArrow {

    public NormalArrow(@NotNull ItemStack item) {
        super(item);
    }

    @Override
    public void onProjectileHit(@NotNull Shooter shooter, @NotNull ProjectileTarget target, @NotNull Block block, @NotNull BlockFace hitFace) {
        target.removeTarget(block);
    }

}
