package net.illager.welcome;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class PlayerJoinListener implements Listener {
    
    Welcome plugin;
    
    public PlayerJoinListener(Welcome plugin) {
        this.plugin = plugin;
    }
        
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()) {
            player.getInventory().addItem(Welcome.kit);
            this.plugin.remindWelcome(player);
        }
    }
}
