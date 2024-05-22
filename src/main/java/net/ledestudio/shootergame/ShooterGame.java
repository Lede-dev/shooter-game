package net.ledestudio.shootergame;

import net.ledestudio.shootergame.projectile.ProjectileListener;
import net.ledestudio.shootergame.commands.GameCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShooterGame extends JavaPlugin {

    private static ShooterGame instance;

    public static ShooterGame getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getCommandMap().register("shooter", new GameCommand("game"));
        Bukkit.getPluginManager().registerEvents(new ProjectileListener(), this);
    }

}
