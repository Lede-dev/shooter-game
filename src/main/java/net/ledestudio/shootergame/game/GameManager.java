package net.ledestudio.shootergame.game;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.ledestudio.shootergame.shooter.EasyArrowShooter;
import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import net.ledestudio.shootergame.target.EasyArrowTarget;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GameManager {

    private static final Map<UUID, GameController> controllers = Maps.newHashMap();

    public static @NotNull GameController getOrCreateGameController(@NotNull Player player) {
        GameController controller = controllers.get(player.getUniqueId());
        if (controller == null) {
            controller = createGameController(player);
            controllers.put(player.getUniqueId(), controller);
        }
        return controller;
    }

    public static @NotNull GameController createGameController(@NotNull Player player) {
        final Shooter shooter = new EasyArrowShooter(player);
        final ProjectileTarget target = new EasyArrowTarget();
        return new ArrowShooterGameController(shooter, target);
    }

    public static boolean hasGameController(@NotNull Player player) {
        return controllers.containsKey(player.getUniqueId());
    }

    public static @NotNull Set<GameController> getGameControllers() {
        return Sets.newHashSet(controllers.values());
    }

}
