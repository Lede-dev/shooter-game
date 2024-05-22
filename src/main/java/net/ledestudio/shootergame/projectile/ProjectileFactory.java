package net.ledestudio.shootergame.projectile;

import net.ledestudio.shootergame.item.TagItemCreator;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class ProjectileFactory {

    protected final @NotNull TagItemCreator creator;

    public ProjectileFactory(@NotNull TagItemCreator projectileCreator) {
        this.creator = projectileCreator;
    }

    public abstract @NotNull AbstractProjectile getOrCreateProjectile(@NotNull Class<? extends AbstractProjectile> type);

    public abstract @NotNull AbstractProjectile createProjectile(@NotNull Class<? extends AbstractProjectile> type);

    public final @NotNull TagItemCreator getTagItemCreator() {
        return creator;
    }

    public boolean isProjectile(@Nullable ItemStack item) {
        return creator.isTagItem(item);
    }

    public abstract @NotNull Optional<AbstractProjectile> byItemStack(@NotNull ItemStack item);

}
