package net.ledestudio.shootergame.target;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.ledestudio.shootergame.shooter.Shooter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EasyArrowTarget implements ProjectileTarget {

    private static final int DISTANCE = 20;
    private static final int SIZE = 7;

    private final List<Block> targetBlocks = Lists.newArrayList();

    @Override
    public void setup(@NotNull Location operatorLoc) {
        clear();

        Location targetLoc = operatorLoc.clone().add(operatorLoc.getDirection().multiply(DISTANCE));
        Vector crossVec = operatorLoc.getDirection().crossProduct(new Vector(0, 1, 0));

        final double crossVecX = crossVec.getX();
        final double crossVecZ = crossVec.getZ();

        if (Math.abs(crossVecX) > Math.abs(crossVecZ)) {
            crossVec = new Vector(Math.round(crossVec.getX()), 0, 0);
        } else {
            crossVec = new Vector(0, 0, Math.round(crossVec.getZ()));
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Vector vec = crossVec.clone().multiply(j);
                Location loc = targetLoc.clone().add(vec);
                Block block = operatorLoc.getWorld().getBlockAt(loc);
                block.setType(Material.TARGET);
                targetBlocks.add(block);
            }
            targetLoc.add(0, 1, 0);
        }
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
        return shooter.getBukkitPlayer().getLocation().distance(block.getLocation()) >= DISTANCE;
    }

}
