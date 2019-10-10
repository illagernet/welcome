package net.illager.welcome;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Welcome extends JavaPlugin {
    
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable() {
        // Do nothing
    }
    
    // Player joins the server
    class PlayerJoinListener implements Listener {
        
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            
            // New player
            if(!player.hasPlayedBefore()) {
                
                // Give kit items
                player.getInventory().addItem(getKit());
                
                // Remind other players to welcome the new player
                remindWelcome(player);
            }
        }
    }
    
    // Prepares a welcome kit
    ItemStack[] getKit() {
        ItemStack[] kit = { new ItemStack(Material.TOTEM_OF_UNDYING), composeWelcomeBook() };
        return kit;
    }
    
    // Prepare a dynamic welcome book item
    ItemStack composeWelcomeBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        
        // Reload the plugin configuration file
        this.reloadConfig();
        
        // Set metadata fields
        meta.setTitle(this.getConfig().getString("welcome-book.title"));
        meta.setAuthor(this.getConfig().getString("welcome-book.author"));
        meta.setGeneration(this.getGeneration(this.getConfig().getInt("welcome-book.generation")));
        
        // Add pages
        for(String page : (List<String>) this.getConfig().getList("welcome-book.generation")) {
            meta.addPage(page);
        }
        
        book.setItemMeta(meta);
        return book;
    }
    
    // Get a book generation from ID
    BookMeta.Generation getGeneration(int generation) {
        switch(generation) {
            case 0: return BookMeta.Generation.ORIGINAL;
            case 1: return BookMeta.Generation.COPY_OF_ORIGINAL;
            case 2: return BookMeta.Generation.COPY_OF_COPY;
            default: return BookMeta.Generation.TATTERED;
        }
    }
    
    // Remind other players to welcome the new player
    void remindWelcome(Player newPlayer) {
        
        // For each player online
        for(Player player : this.getServer().getOnlinePlayers()) {
            
            // Not the new player
            if(!player.getUniqueId().toString().equals(newPlayer.getUniqueId().toString())) {
                player.sendMessage(ChatColor.GOLD + "Welcome " + ChatColor.WHITE + newPlayer.getDisplayName() + ChatColor.GOLD + " to the server!");
            }
        }
    }
}
