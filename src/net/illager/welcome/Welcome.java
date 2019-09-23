package net.illager.welcome;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Welcome extends JavaPlugin {
    
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }
    
    @Override
    public void onDisable() {
        // Do nothing
    }
    
    /**
     * The welcome kit
     */
    public static ItemStack[] kit = {
        new ItemStack(Material.TOTEM_OF_UNDYING),
        Welcome.welcomeBook()
    };
    
    /**
     * Generate a Welcome Guide book
     * @return a written book item
     */
    public static ItemStack welcomeBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setTitle("Welcome Guide");
        meta.setAuthor("martyrboy");
        meta.setGeneration(BookMeta.Generation.TATTERED);
        meta.addPage(
            "Welcome to Illager Net\n\nSemi-Anarchy Enhanced Vanilla Hardcore. On death, you will be banned for "
            + ChatColor.UNDERLINE
            + "72 hours"
            + ChatColor.RESET
            + ".\n\nTake this free "
            + ChatColor.YELLOW
            + "Totem of Undying"
            + ChatColor.RESET
            + " to help you get started."
        );
        meta.addPage(
            ChatColor.UNDERLINE
            + "Rules"
            + ChatColor.RESET
            + "\n\n\u2202 Do not abuse client hacks"
            + "\n\u2202 No malicious use of scripts or bots"
            + "\n\u2202 Be respectful in chat"
            + "\n\u2202 Raiding, Griefing, and PvP permitted"
            + "\n\u2202 Use common sense"
            + "\n\nModerators reserve the right to take action against a player at their discretion."
        );
        meta.addPage(
            ChatColor.UNDERLINE
            + "Discord"
            + ChatColor.RESET
            + "\n\nJoin the Discord server at "
            + ChatColor.BLUE
            + "illager.net/discord"
            + ChatColor.RESET
            + "\n\nLink your account by running the command "
            + ChatColor.GRAY
            + "/discord link"
            + ChatColor.RESET
            + " in-game."
        );
        meta.addPage(
            ChatColor.UNDERLINE
            + "Vanilla Tweaks"
            + ChatColor.RESET
            + "\n\nFor more information on the enhanced vanilla features, including extra crafting recipes, container locks, random spawn, and the Sorcery system, visit "
            + ChatColor.BLUE
            + "illager.net/features"
            + ChatColor.RESET
            + "."
        );
        meta.addPage("Good luck and have fun.");
        book.setItemMeta(meta);
        return book;
    }
    
    /**
     * Remind all other players to welcome a new player
     * @param newPlayer the player being welcomed
     */
    public void remindWelcome(Player newPlayer) {
        for(Player player : this.getServer().getOnlinePlayers()) {
            if(player.getUniqueId().toString() != newPlayer.getUniqueId().toString()) {
                player.sendMessage(
                   ChatColor.GOLD
                   + "Welcome "
                   + ChatColor.WHITE
                   + newPlayer.getDisplayName()
                   + ChatColor.GOLD
                   + " to the server!"
                );
           }
        }
    }
}
