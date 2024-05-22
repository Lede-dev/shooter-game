package net.ledestudio.shootergame.projectile.handler;

import net.ledestudio.shootergame.ShooterGame;
import net.ledestudio.shootergame.game.GameController;
import net.ledestudio.shootergame.game.GameManager;
import net.ledestudio.shootergame.projectile.AbstractProjectile;
import net.ledestudio.shootergame.shooter.Shooter;
import net.ledestudio.shootergame.target.ProjectileTarget;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Optional;

public class ProjectileHandler implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // 타겟 블록이 존재하는지 확인
        final Block hitBlock = event.getHitBlock();
        if (hitBlock == null) {
            return;
        }

        final BlockFace hitBlockFace = event.getHitBlockFace();
        if (hitBlockFace == null) {
            return;
        }

        // 투사체를 날린 사람이 플레이어인지 확인
        final Projectile entity = event.getEntity();
        if (!(entity.getShooter() instanceof Player player)) {
            return;
        }

        // 플레이어가 게임에 참가중인지 확인
        if (!GameManager.hasGameController(player)) {
            return;
        }

        // 플레이어가 게임을 진행중인지 확인
        final GameController controller = GameManager.getOrCreateGameController(player);
        if (!controller.isStarted()) {
            return;
        }

        // 맞춘 블록이 타겟 블록이 맞는지 확인
        final ProjectileTarget target = controller.getTarget();
        if (!target.isTargetBlock(hitBlock)) {
            return;
        }

        // 맞춘 블록이 슈터에게 유효한 타겟인지 확인 (거리 체크 등)
        final Shooter shooter = controller.getShooter();
        if (!target.isAvailableTarget(shooter, hitBlock)) {
            return;
        }

        // 투사체 엔티티에 투사체 데이터가 존재하는지 확인
        List<MetadataValue> datas = event.getEntity().getMetadata("shooter-game");
        if (datas.isEmpty()) {
            return;
        }

        // 모든 조건이 일치한다면 투사체 피격 콜백 실행
        for (MetadataValue data : datas) {
            if (data.value() instanceof AbstractProjectile projectile) {
                projectile.onProjectileHit(shooter, target, hitBlock, hitBlockFace);
                break;
            }
        }

        // 화살 엔티티 제거
        event.getEntity().remove();

        // 모든 타겟을 제거했거나, 플레이어에게 투사체가 남아있지 않다면 게임 종료
        if (!target.hasRemainTarget()) {
            controller.stopGame();
            player.sendMessage("모든 목표를 제거했습니다. 게임을 종료합니다.");
            return;
        }

        if (!shooter.hasRemainProjectile()) {
            controller.stopGame();
            player.sendMessage("투사체가 부족합니다. 게임을 종료합니다.");
            return;
        }
    }

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent event) {

        // 화살을 발사한 엔티티가 플레이어인지 확인
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // 플레이어가 게임에 참가중인지 확인
        if (!GameManager.hasGameController(player)) {
            return;
        }

        // 플레이어가 게임을 진행중인지 확인
        final GameController controller = GameManager.getOrCreateGameController(player);
        if (!controller.isStarted()) {
            return;
        }

        // 유효한 화살인지 확인
        final ItemStack arrow = event.getConsumable();
        if (arrow == null) {
            return;
        }

        // 화살이 등록된 투사체인지 확인
        if (!controller.getProjectileFactory().isProjectile(arrow)) {
            return;
        }

        // 화살 아이템으로부터 등록된 투사체를 가져올 수 있는지 확인
        final Optional<AbstractProjectile> absArrow = controller.getProjectileFactory().byItemStack(arrow);
        if (absArrow.isEmpty()) {
            return;
        }

        // 화살 엔티티에 가져온 투사체 테이터를 주입
        final Entity entity = event.getProjectile();
        entity.setMetadata("shooter-game", new FixedMetadataValue(ShooterGame.getInstance(), absArrow.get()));
    }

}
