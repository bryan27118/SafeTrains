package com.finalfrontiermc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.finalfrontiermc.listeners.Listeners;
 
public final class SafeTrain extends JavaPlugin 
{ 
	private static List<String> inMinecart = new ArrayList<String>();
	public static SafeTrain instance;
	public static Logger logger = Logger.getLogger("Minecraft");

    @Override
    public void onEnable(){
    	instance = this;
    	/*
    	try {
			loadMinecartList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	getServer().getPluginManager().registerEvents(new Listeners(), this);
    }
 
    @Override
    public void onDisable() {
    	/*
    	try {
			saveMinecartList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
    
    public static SafeTrain getInstance()
    {
    	return instance;
    }
    
    public List<String> getMinecartList()
    {
    	return inMinecart;
    }
    
    public void addPlayerToMinecartList(Player p)
    {
    	if(!inMinecart.contains(p.getName()))
    	{
    		logger.log(Level.INFO, p.getName() + " has been added to the minecart list.");
    		inMinecart.add(p.getName());
    	}
    }
    
    public void removePlayerFromMinecartList(Player p)
    {
    	if(inMinecart.contains(p.getName()))
    	{
    		logger.log(Level.INFO, p.getName() + " has been removed from minecart list.");
    		inMinecart.remove(p.getName());
    	}
    }
    
    public void saveMinecartList() throws IOException
    {
    	FileOutputStream fos = new FileOutputStream("playerminecart.data");
    	ObjectOutputStream oos = new ObjectOutputStream(fos);
    	oos.writeObject(inMinecart);
    	oos.close();
    }
    
    @SuppressWarnings("unchecked")
	public void loadMinecartList() throws IOException, ClassNotFoundException
    {
    	FileInputStream fis = new FileInputStream("playerminecart.data");
    	ObjectInputStream ois = new ObjectInputStream(fis);
    	inMinecart = (List<String>) ois.readObject();
    	ois.close();
    }
    
    
}