package fi.silentnoodlemaster.helloword;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelloWorld extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		String name = event.getPlayer().getName();
		String message = this.getConfig().getString("message");
		String command = String.format("tellraw %s %s", name, message);
		getServer().dispatchCommand(getServer().getConsoleSender(), command);
	}
}
