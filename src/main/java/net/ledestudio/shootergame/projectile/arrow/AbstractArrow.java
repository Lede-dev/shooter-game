package net.ledestudio.shootergame.projectile.arrow;

import net.ledestudio.shootergame.projectile.AbstractProjectile;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractArrow extends AbstractProjectile {

    public AbstractArrow(@NotNull ItemStack item) {
        super(item);
    }

}
