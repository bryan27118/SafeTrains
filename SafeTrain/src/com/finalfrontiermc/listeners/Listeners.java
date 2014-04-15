package com.finalfrontiermc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import com.finalfrontiermc.SafeTrain;

public class Listeners implements Listener
{
   @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
    {
	   if(e.getEntity() instanceof Player && e.getDamager() instanceof Player)
	   {
		   if(SafeTrain.getInstance().getMinecartList().contains(((Player) e.getEntity()).getName()) && SafeTrain.getInstance().getMinecartList().contains(((Player) e.getDamager()).getName()))
		   {
			   ((Player) e.getDamager()).sendMessage(ChatColor.RED + "No pvp allowed on trains!");
			   e.setCancelled(true);
		   }
	   }
	}
   
   @EventHandler
   public void onEntityDamage(EntityDamageEvent e)
   {
	   if(e.getEntity() instanceof Player)
	   {
		   if(SafeTrain.getInstance().getMinecartList().contains(((Player) e.getEntity()).getName()))
		   {
			   e.setCancelled(true);
		   }
	   }
	}
   
   @EventHandler
   public void onVehicleEnterEvent(VehicleEnterEvent event)
   {
	   Player player = (Player) event.getEntered();

	   if(player instanceof Player)
	   {
		   if(event.getVehicle() instanceof Minecart)
		   {
			   SafeTrain.getInstance().addPlayerToMinecartList(player);
		   }
	   }
   }
   
   @EventHandler
   public void onVehicleExitEvent(VehicleExitEvent event)
   {
	   Player player = (Player) event.getExited();

	   if(player instanceof Player)
	   {
		   if(event.getVehicle() instanceof Minecart)
		   {
			   SafeTrain.getInstance().removePlayerFromMinecartList(player);
		   }
	   }
   }
}
