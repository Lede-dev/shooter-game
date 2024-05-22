package net.ledestudio.shootergame.game;

import net.ledestudio.shootergame.projectile.ProjectileFactory;
import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.jetbrains.annotations.NotNull;

public interface GameController {

    /**
     * 게임이 진행중이라면 게임을 진행중인 플레이어를 반환한다.
     * @return 게임을 진행중인 플레이어
     */
    @NotNull Shooter getShooter();

    /**
     * 게임이 진행중이라면 게임을 진행중인 플레이어의 타겟을 반환한다.
     * @return 게임 타겟
     */
    @NotNull ProjectileTarget getTarget();

    /**
     * 화살의 생성을 담당한다.
     * @return 화살 생성 팩토리
     */
    @NotNull ProjectileFactory getProjectileFactory();

    /**
     * 게임이 진행중인지 확인한다.
     * @return 게임이 진행중이라면 true, 그렇지 않다면 false
     */
    boolean isStarted();

    /**
     * 진행중인 게임이 없다면 새로운 게임을 시작한다.
     */
    void startGame();

    /**
     * 진행중인 게임을 종료한다.
     */
    void stopGame();

}
