package net.ledestudio.shootergame.target;

import net.ledestudio.shootergame.shooter.Shooter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface ProjectileTarget {

    void setup(@NotNull Location operatorLoc);

    void clear();

    void removeTarget(@NotNull Block block);

    boolean hasRemainTarget();

    boolean isTargetBlock(@NotNull Block block);

    boolean isAvailableTarget(@NotNull Shooter shooter, @NotNull Block block);

}
