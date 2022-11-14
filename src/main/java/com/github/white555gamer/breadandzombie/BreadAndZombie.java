package com.github.white555gamer.breadandzombie;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BreadAndZombie extends JavaPlugin implements Listener {

    private static BreadAndZombie instance;

    public static Boolean isActive = false;
    public static Integer BreadAndZombieCount = 1;

    public BreadAndZombie() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("breadandzombie").setExecutor(new BreadAndZombieCommand());
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {}

    @EventHandler
    public void onPlayerJumpEvent(PlayerJumpEvent event) {
        if (isActive) {
            for (int i = 1; i <= BreadAndZombieCount; i++) {
                event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation().add(0,5,0), EntityType.ZOMBIE);
            }
        }
    }

    public static BreadAndZombie getInstance() {
        return instance;
    }
}
