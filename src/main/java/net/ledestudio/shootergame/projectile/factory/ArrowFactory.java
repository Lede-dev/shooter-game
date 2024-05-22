package net.ledestudio.shootergame.projectile.factory;

import com.google.common.collect.Maps;
import net.kyori.adventure.text.Component;
import net.ledestudio.shootergame.item.ArrowTagItemCreator;
import net.ledestudio.shootergame.projectile.AbstractProjectile;
import net.ledestudio.shootergame.projectile.ProjectileFactory;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public class ArrowFactory extends ProjectileFactory {

    private final Map<Class<? extends AbstractProjectile>, AbstractProjectile> arrows = Maps.newHashMap();

    public ArrowFactory() {
        super(new ArrowTagItemCreator());
    }

    @Override
    public @NotNull AbstractProjectile getOrCreateProjectile(@NotNull Class<? extends AbstractProjectile> type) {
        AbstractProjectile arrow = arrows.get(type);
        if (arrow == null) {
            arrow = createProjectile(type);
        }
        return arrow;
    }

    @Override
    public @NotNull AbstractProjectile createProjectile(@NotNull Class<? extends AbstractProjectile> type) {
        try {
            ItemStack item = creator.createItem(type.getSimpleName());
            AbstractProjectile projectile = type.getConstructor(ItemStack.class).newInstance(item);
            arrows.put(type, projectile);
            return projectile;
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 클래스 입력. Class: " + type.getName());
        }
    }

    @Override
    public @NotNull Optional<AbstractProjectile> byItemStack(@NotNull ItemStack item) {
        final Optional<String> itemTag = creator.getTag(item);
        if (itemTag.isPresent()) {
            for (AbstractProjectile arrow : arrows.values()) {
                final Optional<String> arrowTag = creator.getTag(arrow.getProjectileItemStack());
                if (arrowTag.isPresent()) {
                    if (itemTag.get().equals(arrowTag.get())) {
                        return Optional.of(arrow);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
