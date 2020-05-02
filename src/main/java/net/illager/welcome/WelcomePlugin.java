package net.illager.welcome;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion.Target;

@Plugin(name = "Welcome", version = "1.0.0")
@Description(value = "Greet new players and give them starter items")
@ApiVersion(value = Target.v1_13)
public class WelcomePlugin extends JavaPlugin {

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
