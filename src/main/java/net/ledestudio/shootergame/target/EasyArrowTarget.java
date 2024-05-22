package net.ledestudio.shootergame.target;

import com.google.common.collect.Lists;
import net.ledestudio.shootergame.shooter.Shooter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EasyArrowTarget implements ProjectileTarget {

    private List<Block> targetBlocks = Lists.newArrayList();

    @Override
    public void setup(@NotNull Location operatorLoc) {

    }

    @Override
    public void clear() {
        for (Block block : targetBlocks) {
            block.setType(Material.AIR);
        }
        targetBlocks.clear();
    }

    @Override
    public void removeTarget(@NotNull Block block) {
        if (isTargetBlock(block)) {
            block.setType(Material.AIR);
            targetBlocks.remove(block);
        }
    }

    @Override
    public boolean hasRemainTarget() {
        return !targetBlocks.isEmpty();
    }

    @Override
    public boolean isTargetBlock(@NotNull Block block) {
        return targetBlocks.contains(block);
    }

    @Override
    public boolean isAvailableTarget(@NotNull Shooter shooter, @NotNull Block block) {
        return shooter.getBukkitPlayer().getLocation().distance(block.getLocation()) >= 20;
    }

}
