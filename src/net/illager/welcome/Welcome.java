package net.illager.welcome;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Welcome extends JavaPlugin {
    
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable() {
        // Do nothing
    }
    
    /**
     * Prepares a welcome kit
     */
    public ItemStack[] getKit() {
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        this.reloadConfig();
        meta.setTitle(this.getConfig().getString("welcome-book.title"));
        meta.setAuthor(this.getConfig().getString("welcome-book.author"));
        switch(this.getConfig().getInt("welcome-book.generation")) {
            case 0:
                meta.setGeneration(BookMeta.Generation.ORIGINAL);
                break;
            case 1:
                meta.setGeneration(BookMeta.Generation.COPY_OF_ORIGINAL);
                break;
            case 2:
                meta.setGeneration(BookMeta.Generation.COPY_OF_COPY);
                break;
            default:
                meta.setGeneration(BookMeta.Generation.TATTERED);
        }
        for(String page : (List<String>) this.getConfig().getList("welcome-book.generation")) {
            meta.addPage(page);
        }
        book.setItemMeta(meta);
        ItemStack[] kit = { totem, book };
        return kit;
    }
    
    /**
     * Remind all other players to welcome a new player
     * @param newPlayer the player being welcomed
     */
    public void remindWelcome(Player newPlayer) {
        for(Player player : this.getServer().getOnlinePlayers()) {
            if(player.getUniqueId().toString() != newPlayer.getUniqueId().toString()) {
                player.sendMessage(ChatColor.GOLD + "Welcome " + ChatColor.WHITE + newPlayer.getDisplayName() + ChatColor.GOLD + " to the server!");
            }
        }
    }
}
