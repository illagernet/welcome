package net.illager.welcome;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
	private Welcomer welcomer;

	public PlayerJoin(Welcomer welcomer) {
		this.welcomer = welcomer;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		// If the player has never played before
		if(!player.hasPlayedBefore()) {
			welcomer.giveKit(player);
			welcomer.remindWelcome(player);
		}
	}
}