package net.ledestudio.shootergame.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface TagItemCreator {

    @NotNull ItemStack createItem(@NotNull String value);

    boolean isTagItem(@Nullable ItemStack item);

    @NotNull Optional<String> getTag(@NotNull ItemStack item);

}
