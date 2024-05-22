package net.ledestudio.shootergame.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ArrowTagItemCreator implements TagItemCreator {

    private final NamespacedKey KEY = new NamespacedKey("shooter-game", "arrow-type");

    @Override
    public @NotNull ItemStack createItem(@NotNull String value) {
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("화살 - " + value));
        meta.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean isTagItem(@Nullable ItemStack item) {
        if (item == null) {
            return false;
        }
        return item.getItemMeta().getPersistentDataContainer().has(KEY, PersistentDataType.STRING);
    }

    @Override
    public @NotNull Optional<String> getTag(@NotNull ItemStack item) {
        String value = item.getItemMeta().getPersistentDataContainer().get(KEY, PersistentDataType.STRING);
        return Optional.ofNullable(value);
    }
}
