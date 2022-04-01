package me.jordan.dragondrop;

import me.jordan.dragondrop.commands.DragonDropCommand;
import me.jordan.dragondrop.listeners.DragonPhaseChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonDrop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new DragonPhaseChangeListener(this);
        new DragonDropCommand(this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
