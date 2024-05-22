package net.ledestudio.shootergame.game;

import net.ledestudio.shootergame.projectile.arrow.ArrowFactory;
import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.jetbrains.annotations.NotNull;

public class ArrowShooterGameController implements GameController {

    private final @NotNull Shooter shooter;
    private final @NotNull ProjectileTarget target;
    private final @NotNull ArrowFactory arrowFactory;
    private boolean isStarted;

    public ArrowShooterGameController(@NotNull Shooter shooter, @NotNull ProjectileTarget target) {
        this.shooter = shooter;
        this.target = target;
        this.arrowFactory = new ArrowFactory();
        this.isStarted = false;
    }

    @Override
    public @NotNull Shooter getShooter() {
        return shooter;
    }

    @Override
    public @NotNull ProjectileTarget getTarget() {
        return target;
    }

    @Override
    public @NotNull ArrowFactory getProjectileFactory() {
        return arrowFactory;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void startGame() {
        if (isStarted) {
            throw new IllegalStateException("이미 게임이 진행중입니다.");
        }

        target.setup(shooter.getBukkitPlayer().getLocation());
        shooter.setup(arrowFactory);
        isStarted = true;
    }

    @Override
    public void stopGame() {
        if (!isStarted) {
            throw new IllegalStateException("게임이 진행중이지 않습니다.");
        }

        target.clear();
        shooter.clear();
        isStarted = false;
    }
}
