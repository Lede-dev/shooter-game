package net.ledestudio.shootergame.commands;

import com.google.common.collect.Lists;
import net.ledestudio.shootergame.game.GameController;
import net.ledestudio.shootergame.game.GameManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameCommand extends BukkitCommand {

    public GameCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "start" -> {
                GameController controller = GameManager.getOrCreateGameController(player);
                if (controller.isStarted()) {
                    player.sendMessage("이미 게임이 진행중입니다.");
                    return false;
                }
                controller.startGame();
                player.sendMessage("게임을 시작합니다.");
            }
            case "stop" -> {
                GameController controller = GameManager.getOrCreateGameController(player);
                if (!controller.isStarted()) {
                    player.sendMessage("아직 게임이 시작되지 않았습니다.");
                    return false;
                }
                controller.stopGame();
                player.sendMessage("게임을 종료합니다.");
            }
        }

        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return Lists.newArrayList("start", "stop");
        }
        return Lists.newArrayList();
    }
}
