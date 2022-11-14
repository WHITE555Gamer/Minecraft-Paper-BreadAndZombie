package com.github.white555gamer.breadandzombie;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static com.github.white555gamer.breadandzombie.BreadAndZombie.BreadAndZombieCount;
import static org.bukkit.Material.BREAD;

public class ItemDeliverTask {

    private final BukkitTask runnable;

    public ItemDeliverTask(JavaPlugin plugin) {
        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.getInventory().addItem(new ItemStack(BREAD, BreadAndZombieCount));
                });
            }
        }.runTaskTimer(plugin, 0, 100);
    }

    public void cancel() {
        this.runnable.cancel();
    }
}