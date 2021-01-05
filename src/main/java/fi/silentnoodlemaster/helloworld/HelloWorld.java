package fi.silentnoodlemaster.helloworld;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

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
		UUID uuid = event.getPlayer().getUniqueId();
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());

		int[] arrayUuid = new int[4];
		for (int i = 0; i < arrayUuid.length; i++) {
			arrayUuid[i] = bb.getInt(i * 4);
		}
		String outUuid = Arrays.toString(arrayUuid);
		outUuid = outUuid.replace("[", "[I;");
		String message = this.getConfig().getString("message");
		String command = String.format("tellraw @a[nbt={UUID:%s}] %s", outUuid, message);
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
