package fr.yohem.bettertools;

import fr.yohem.bettertools.customEnchant.CustomEnchant;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ToolsListener implements Listener {
    @EventHandler
    public void  useTools(PlayerItemDamageEvent event){
        ItemStack itemStack = event.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        System.out.println("a");
        Player player = event.getPlayer();
        if (CustomEnchant.Enchant.AUTO_REPLANTISH.getEnchant().hasThisEnchant(itemStack) &&(itemStack.getType().equals(Material.DIAMOND_HOE) || itemStack.getType().equals(Material.GOLD_HOE) || itemStack.getType().equals(Material.WOOD_HOE) || itemStack.getType().equals(Material.IRON_HOE)  || itemStack.getType().equals(Material.STONE))) {
            System.out.println("diamond hoe");
            if (block.getType().equals(Material.GRASS) || block.getType().equals(Material.DIRT)  || block.getType().equals(Material.SOIL) ) {
                System.out.println("dirt");
                Material seed = Material.AIR;
                if (player.getInventory().contains(Material.SEEDS))
                    seed = Material.SEEDS;
                else if (player.getInventory().contains(Material.MELON_SEEDS))
                    seed = Material.MELON_SEEDS;
                else if (player.getInventory().contains(Material.PUMPKIN_SEEDS))
                    seed = Material.MELON_SEEDS;
                else if (player.getInventory().contains(Material.CARROT))
                    seed = Material.CARROT;
                else if (player.getInventory().contains(Material.POTATO))
                    seed = Material.POTATO;
                else if (player.getInventory().contains(Material.BEETROOT_SEEDS))
                    seed = Material.BEETROOT_SEEDS;
                System.out.println(seed);
                if (!seed.equals(Material.AIR) && block.getRelative(0,1,0).getType().equals(Material.AIR)) {
//                            block.getRelative(0,1,0).setType(Material.W);
                    block.getRelative(1,1,0).setType(Material.CARROT);
//                            block.getRelative(1,1,1).setType(Material.POTATO);
//                            block.getRelative(0,1,1).setType(Material.BEETROOT_BLOCK);
//                            block.getRelative(2,1,1).setType(Material.MELON_STEM);
//                            block.getRelative(2,1,1).setType(Material.PUMPKIN_SEEDS);
                }
            }
    }
    @EventHandler
    public void  useTools(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        System.out.println(itemStack.getType() +" "+event.getAction());

        switch (event.getAction()){
            case RIGHT_CLICK_BLOCK:
                if (CustomEnchant.Enchant.AUTO_REPLANTISH.getEnchant().hasThisEnchant(itemStack) &&(itemStack.getType().equals(Material.DIAMOND_HOE) || itemStack.getType().equals(Material.GOLD_HOE) || itemStack.getType().equals(Material.WOOD_HOE) || itemStack.getType().equals(Material.IRON_HOE)  || itemStack.getType().equals(Material.STONE))) {
                    System.out.println("diamond hoe");
                    Block block = event.getClickedBlock();
                    if (block.getType().equals(Material.GRASS) || block.getType().equals(Material.DIRT)  || block.getType().equals(Material.SOIL) ) {
                        System.out.println("dirt");
                        Material seed = Material.AIR;
                        if (player.getInventory().contains(Material.SEEDS))
                            seed = Material.SEEDS;
                        else if (player.getInventory().contains(Material.MELON_SEEDS))
                            seed = Material.MELON_SEEDS;
                        else if (player.getInventory().contains(Material.PUMPKIN_SEEDS))
                            seed = Material.MELON_SEEDS;
                        else if (player.getInventory().contains(Material.CARROT))
                            seed = Material.CARROT;
                        else if (player.getInventory().contains(Material.POTATO))
                            seed = Material.POTATO;
                        else if (player.getInventory().contains(Material.BEETROOT_SEEDS))
                            seed = Material.BEETROOT_SEEDS;
                        System.out.println(seed);
                        if (!seed.equals(Material.AIR) && block.getRelative(0,1,0).getType().equals(Material.AIR)) {
                            event.setCancelled(false);
                            block.setType(Material.SOIL);
                            event.setUseInteractedBlock(Event.Result.DENY);
                            event.setUseItemInHand(Event.Result.ALLOW);
//                            block.getRelative(0,1,0).setType(Material.W);
                            block.getRelative(1,1,0).setType(Material.CARROT);
//                            block.getRelative(1,1,1).setType(Material.POTATO);
//                            block.getRelative(0,1,1).setType(Material.BEETROOT_BLOCK);
//                            block.getRelative(2,1,1).setType(Material.MELON_STEM);
//                            block.getRelative(2,1,1).setType(Material.PUMPKIN_SEEDS);
                        }
                    }
                }
        }


    }
}
