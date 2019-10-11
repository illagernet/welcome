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

/**
 * Plugin class that sends a welcome message
 *
 * @author IllagerNet
 * @version v1.0.0
 */
public class Welcome extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable() {
        // not used
    }

    /**
     * Event Listener
     * Fires when a player joins
     *
     * @param event PlayerJoinEvent Object
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()) { // true if player is new to the server
            player.getInventory().addItem(getKit()); // gives the player the starter kit
            remindWelcome(player); // reminds other players to say welcome
        }
    }

    /**
     * Method that prepares the starter kit
     *
     * @return returns all the items in a ItemStack Array
     */
    private ItemStack[] getKit() {
        return new ItemStack[] { new ItemStack(Material.TOTEM_OF_UNDYING), composeWelcomeBook() };
    }

    /**
     * Method that prepares a book
     *
     * @return the book as an ItemStack object
     */
    private ItemStack composeWelcomeBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        
        // Reload the plugin configuration file
        this.reloadConfig();
        
        // Set metadata fields
        meta.setTitle(this.getConfig().getString("welcome-book.title"));
        meta.setAuthor(this.getConfig().getString("welcome-book.author"));
        meta.setGeneration(this.getGeneration(this.getConfig().getInt("welcome-book.generation")));
        
        // Add pages
        for(String page : (List<String>) this.getConfig().getList("welcome-book.generation"))
            meta.addPage(page);
        
        book.setItemMeta(meta);
        return book;
    }

    /**
     * Method that gets a book generation from an ID
     *
     * @param generation the generation id
     * @return the BookMeta associated with the id
     */
    private BookMeta.Generation getGeneration(int generation) {
        switch(generation) {
            case 0: return BookMeta.Generation.ORIGINAL;
            case 1: return BookMeta.Generation.COPY_OF_ORIGINAL;
            case 2: return BookMeta.Generation.COPY_OF_COPY;
            default: return BookMeta.Generation.TATTERED;
        }
    }

    /**
     * Loops through all the online players and sends a welcome
     * message to all the players (except the new player)
     *
     * @param newPlayer the new player
     */
    private void remindWelcome(Player newPlayer) {
        for(Player player : this.getServer().getOnlinePlayers())
            if(!player.getUniqueId().toString().equals(newPlayer.getUniqueId().toString()))
                player.sendMessage(ChatColor.GOLD + "Welcome " + ChatColor.WHITE + newPlayer.getDisplayName() + ChatColor.GOLD + " to the server!");
    }
}
