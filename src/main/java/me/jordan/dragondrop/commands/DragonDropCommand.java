package me.jordan.dragondrop.commands;

import me.jordan.dragondrop.DragonDrop;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DragonDropCommand implements CommandExecutor {

    private DragonDrop plugin;

    public DragonDropCommand(DragonDrop dragonDrop) {
        this.plugin = dragonDrop;

        plugin.getCommand("dragondrop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("dragondrop")){
            if (args.length >= 3){
                if (args[0].equalsIgnoreCase("drop")){
                    if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
                        if (args[1].equalsIgnoreCase("dragonegg")){
                            plugin.getConfig().set("drop-egg", Boolean.valueOf(args[2]));
                            sender.sendMessage(ChatColor.GREEN + "Drops updated");
                            return true;
                        } else if (args[1].equalsIgnoreCase("dragonhead")){
                            plugin.getConfig().set("drop-head", Boolean.valueOf(args[2]));
                            sender.sendMessage(ChatColor.GREEN + "Drops updated");
                            return true;
                        }else if (args[1].equalsIgnoreCase("elytra")){
                            plugin.getConfig().set("drop-elytra", Boolean.valueOf(args[2]));
                            sender.sendMessage(ChatColor.GREEN + "Drops updated");
                            return true;
                        }
                    }
                }
            }
            sender.sendMessage(ChatColor.RED + "Incorrect use of command do /dragondrop drop dragonhead/dragonegg/elytra true/false");
            return true;
        }
        return false;
    }
}
