package net.ledestudio.shootergame.shooter;

import net.ledestudio.shootergame.projectile.ProjectileFactory;
import net.ledestudio.shootergame.projectile.arrow.HorizontalArrow;
import net.ledestudio.shootergame.projectile.arrow.NormalArrow;
import net.ledestudio.shootergame.projectile.arrow.VerticalArrow;
import net.ledestudio.shootergame.projectile.factory.ArrowFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EasyArrowShooter implements Shooter {

    private final Player player;
    private ProjectileFactory factory;

    public EasyArrowShooter(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull Player getBukkitPlayer() {
        return player;
    }

    @Override
    public void setup(@NotNull ProjectileFactory arrowFactory) {
        factory = arrowFactory;

        // 활 지급
        Inventory inv = player.getInventory();
        inv.addItem(new ItemStack(Material.BOW));

        // 일반 화살 지급
        ItemStack normal = arrowFactory.getOrCreateProjectile(NormalArrow.class).getProjectileItemStack();
        normal.setAmount(30);
        inv.addItem(normal);

        // 세로 화살 지급
        ItemStack vertical = arrowFactory.getOrCreateProjectile(VerticalArrow.class).getProjectileItemStack();
        vertical.setAmount(15);
        inv.addItem(vertical);

        // 가로 화살 지급
        ItemStack horizontal = arrowFactory.getOrCreateProjectile(HorizontalArrow.class).getProjectileItemStack();
        horizontal.setAmount(15);
        inv.addItem(horizontal);
    }

    @Override
    public void clear() {
        player.getInventory().clear();
    }

    @Override
    public boolean hasRemainProjectile() {
        if (factory == null) {
            return false;
        }

        for (ItemStack content : player.getInventory()) {
            if (factory.isProjectile(content)) {
                return true;
            }
        }
        return false;
    }

}
