package net.ledestudio.shootergame.projectile.arrow;

import net.kyori.adventure.text.Component;
import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class HorizontalArrow extends AbstractArrow {

    public HorizontalArrow(@NotNull ItemStack item) {
        super(item);
    }

    @Override
    public void onProjectileHit(@NotNull Shooter shooter, @NotNull ProjectileTarget target, @NotNull Block block, @NotNull BlockFace hitFace) {
        Bukkit.broadcast(Component.text("On Horizontal Arrow Hit"));

        Vector vec = hitFace.getDirection().crossProduct(new Vector(0, 1, 0));
        target.removeTarget(block.getRelative(vec.getBlockX(), 0, vec.getBlockZ()));
        target.removeTarget(block.getRelative(-vec.getBlockX(), 0, -vec.getBlockZ()));
        target.removeTarget(block);
    }
}
