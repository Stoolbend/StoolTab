package me.Stoolbend.Stooltab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.config.Configuration;

public class Listener extends PlayerListener{

	public static Stooltab plugin;
	protected static Configuration config;
	
	public Listener(Stooltab instance){
		plugin = instance;
	}
	
	public static Map<Player, String> displayName = new HashMap<Player, String>();
	public static Map<Player, Integer> nameLength = new HashMap<Player, Integer>();
	
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
	    
	    //Get the player's friendly name
	    displayName.put(player, player.getDisplayName());
	    
	    //Import the config data
	    config = Stooltab.config;
	    config.load();
	    
	    List<String> defaultRanks = Arrays.asList("[OWNER]", "[MEMBER]", "[GUEST]"); // Set default ranks
	    List<String> defaultNames = Arrays.asList("Stoolbend", "DominionSpy", "Aslheybeeeee"); // Set default ranks
	    
	    //Find the length of the players DisplayName
	    nameLength.put(player, displayName.get(player).length());
	    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	    
	    //Get the rank aray
	    List<String> ranksList = config.getStringList("ranks", defaultRanks);
	    
	    for(int i1 = 0; i1 < ranksList.size(); i1++)
	    {
	    	if(nameLength.get(player) > 14)
		    {
	    	displayName.put(player, displayName.get(player).replace(ranksList.get(i1), ""));
		    }
	    }
	    
	    //Get the hidden player array
	    List<String> hiddenList = config.getStringList("hiddenplayers", defaultNames);
	    
	    for(int i2 = 0; i2 < hiddenList.size(); i2++)
	    {
	    	if(player.getName().equalsIgnoreCase(hiddenList.get(i2)))
	    	{
	    		displayName.put(player, "- Hidden -");
	    	}
	    }
	    
	    // Emergency name shortening protocol!
	    if(nameLength.get(player) > 14)
	    {
	    	displayName.put(player, displayName.get(player).substring(0, 11));
	    	displayName.put(player, displayName.get(player)+ChatColor.WHITE+"...");
	    }
	    
	    // Set the ListName as the new display name
	    player.setPlayerListName(displayName.get(player));
	}
}
