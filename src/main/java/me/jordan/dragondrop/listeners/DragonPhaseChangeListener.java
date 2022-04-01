package me.jordan.dragondrop.listeners;

import me.jordan.dragondrop.DragonDrop;
import org.bukkit.*;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

public class DragonPhaseChangeListener implements Listener {

    private DragonDrop plugin;

    public DragonPhaseChangeListener(DragonDrop dragonDrop) {
        this.plugin = dragonDrop;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void DragonPhaseChange(EnderDragonChangePhaseEvent e){
        if (e.getNewPhase() == EnderDragon.Phase.DYING){
            Location portalLocation = e.getEntity().getDragonBattle().getEndPortalLocation();
            World world = portalLocation.getWorld();
            Location dropLocation = portalLocation.clone().add(0.5, 4, 0.5);

            new BukkitRunnable(){
                Location loc = dropLocation.clone();
                double t = 0;
                double r = 0.5;
                @Override
                public void run() {
                    if (t == 0){
                        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

                        Team team = board.registerNewTeam("purple");
                        team.setColor(ChatColor.LIGHT_PURPLE);

                        if (plugin.getConfig().getBoolean("drop-elytra")){
                            Item item = world.dropItem(dropLocation, new ItemStack(Material.ELYTRA));
                            item.setGlowing(true);
                            item.setVelocity(new Vector(0, 0, 0));
                            team.addEntry(item.getUniqueId().toString());
                        }
                        if (plugin.getConfig().getBoolean("drop-head")){
                            Item item = world.dropItem(dropLocation, new ItemStack(Material.DRAGON_HEAD));
                            item.setGlowing(true);
                            item.setVelocity(new Vector(0, 0, 0));
                            team.addEntry(item.getUniqueId().toString());
                        }
                        if (plugin.getConfig().getBoolean("drop-egg")){
                            Item item = world.dropItem(dropLocation, new ItemStack(Material.DRAGON_EGG));
                            item.setGlowing(true);
                            item.setVelocity(new Vector(0, 0, 0));
                            team.addEntry(item.getUniqueId().toString());
                        }
                    }
                    t = t + Math.PI/8;
                    double x = r*Math.cos(t);
                    double y = t/6;
                    double z = r*Math.sin(t);
                    loc.add(x,y,z);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(128, 0, 128), 1.0F);
                    world.spawnParticle(Particle.REDSTONE, loc, 50, dustOptions);
                    loc.subtract(x,y,z);
                    if (t > Math.PI*4){
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 200,1);
        }
    }

}
