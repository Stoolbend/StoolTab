package me.Stoolbend.Stooltab;

import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class Stooltab extends JavaPlugin{

	Logger log = Logger.getLogger("Minecraft");

	  private int taskId = 0;
	  public static Server server;
	  public static Configuration config;

	  public void onDisable()
	  {
	    getServer().getScheduler().cancelTask(this.taskId);
	    this.log.info("[TAB] StoolTab - DISABLED");
	  }

	  public void onEnable() {
	    this.log.info("[TAB] Loading plugin...");
	    
	    config = getConfiguration();
	    
	    if (config.getInt("system", 0) == 0) {
	        this.log.info("[TAB] WARN! - config.yml was not found or corrupted!");
	        config.setHeader("# StoolTab 1.5 - This is a YAML File, Remember people, NO TABS! Only  S p a c e s.");
	        config.setProperty("ranks", new String [] {"[OWNER]", "[MOD]", "[ADMIN]", "[XPB]"});
	        config.setProperty("hiddenplayers", new String [] {"Stoolbend", "DominionSpy", "Aslheybeeeee"});
	        config.setProperty("system", Integer.valueOf(1));
	        config.save();
	        this.log.info("[TAB] WARN! - Generated new config.yml");
	      }
	    
	    PluginManager pm = this.getServer().getPluginManager();
	    Listener playerListener = new Listener(this);
	    pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
	    
	    this.log.info("[TAB] StoolTab - ENABLED");
	  }

}
