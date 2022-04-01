package me.jordan.dragondrop;

import me.jordan.dragondrop.listeners.DragonPhaseChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonDrop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new DragonPhaseChangeListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
