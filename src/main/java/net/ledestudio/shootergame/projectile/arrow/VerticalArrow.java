package net.ledestudio.shootergame.projectile.arrow;

import net.kyori.adventure.text.Component;
import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class VerticalArrow extends AbstractArrow {

    public VerticalArrow(@NotNull ItemStack item) {
        super(item);
    }

    @Override
    public void onProjectileHit(@NotNull Shooter shooter, @NotNull ProjectileTarget target, @NotNull Block block, @NotNull BlockFace hitFace) {
        Bukkit.broadcast(Component.text("On Vertical Arrow Hit"));

        target.removeTarget(block.getRelative(0,1,0));
        target.removeTarget(block.getRelative(0, -1, 0));
        target.removeTarget(block);
    }

}
