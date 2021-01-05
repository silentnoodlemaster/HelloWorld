package fi.silentnoodlemaster.helloword;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
		String uuid = event.getPlayer().getUniqueId().toString();
		String message = this.getConfig().getString("message");
		String command = String.format("tellraw %s %s", uuid, message);
		getServer().dispatchCommand(getServer().getConsoleSender(), command);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reloadhello")) {
			this.reloadConfig();
			sender.sendMessage("Reloaded config!");
			return true;
		}
		return false; 
	}
}
