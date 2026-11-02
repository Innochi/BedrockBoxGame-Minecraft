package com.inkarius.bedrockbox.commands;

import com.inkarius.bedrockbox.managers.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetupCommand implements CommandExecutor {
    private final ConfigManager configManager;

    public SetupCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Команда доступна только игрокам.");
            return true;
        }

        if (!player.hasPermission("bedrockbox.admin")) {
            player.sendMessage("§cНедостаточно прав.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§e/bb setlobby §7- Установить главное лобби");
            player.sendMessage("§e/bb create <арена> §7- Создать новую арену");
            player.sendMessage("§e/bb setcenter <арена> §7- Установить продавца в центре");
            player.sendMessage("§e/bb setshop <арена> <команда> §7- Установить продавца команды");
            player.sendMessage("§e/bb setbed <арена> <команда> §7- Установить кровать команды");
            return true;
        }

        String action = args[0].toLowerCase();

        switch (action) {
            case "setlobby":
                configManager.setLobby(player.getLocation());
                player.sendMessage("§aЛобби установлено на ваших координатах!");
                break;

            case "create":
                if (args.length < 2) {
                    player.sendMessage("§cИспользование: /bb create <арена>");
                    return true;
                }
                configManager.createArena(args[1]);
                player.sendMessage("§aАрена " + args[1] + " инициализирована.");
                break;

            case "setcenter":
                if (args.length < 2) {
                    player.sendMessage("§cИспользование: /bb setcenter <арена>");
                    return true;
                }
                configManager.setCenterShop(args[1], player.getLocation());
                player.sendMessage("§aЦентральный торговец для " + args[1] + " установлен.");
                break;

            case "setshop":
                if (args.length < 3) {
                    player.sendMessage("§cИспользование: /bb setshop <арена> <команда>");
                    return true;
                }
                configManager.setTeamShop(args[1], args[2], player.getLocation());
                player.sendMessage("§aТорговец команды " + args[2] + " на арене " + args[1] + " установлен.");
                break;

            case "setbed":
                if (args.length < 3) {
                    player.sendMessage("§cИспользование: /bb setbed <арена> <команда>");
                    return true;
                }
                configManager.setTeamBed(args[1], args[2], player.getLocation());
                player.sendMessage("§aКровать команды " + args[2] + " на арене " + args[1] + " установлена.");
                break;

            default:
                player.sendMessage("§cНеизвестная команда. Используйте /bb");
                break;
        }
        return true;
    }
}
