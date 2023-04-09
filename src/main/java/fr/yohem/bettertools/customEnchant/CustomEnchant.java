package fr.yohem.bettertools.customEnchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomEnchant{
    private String name;
    private LevelCustomEnchant level;

    public enum Enchant{
        AUTO_REPLANTISH("Auto-replantish", 1),
        BIGBUGER("Auto-recup", 3),
        FURNACE("chaleur infernal", 3),
        CAMPAGNARD("esprit campagnard", 3);
        CustomEnchant customEnchant;

        public CustomEnchant getEnchant(){
            return customEnchant;
        }

        Enchant(String bigbuger, int i) {
            customEnchant = new CustomEnchant(bigbuger, i);
        }
    }
    public CustomEnchant(String name, int level) {
        System.out.println("name :"+name);
        name = Character.toString(name.charAt(0)).toUpperCase()+name.replaceFirst(name.charAt(0)+"", "") ;
        this.name = name;
        this.level = new LevelCustomEnchant(level);
    }


    public boolean hasThisEnchant(ItemStack itemStack){
        List<String> lore = itemStack.getItemMeta().getLore();
        if (findLineEnchant(itemStack)>=0){
            String line = lore.get(findLineEnchant(itemStack));
            if (line.contains(name))
                return true;
        }

        return false;
    }

    public int findLineEnchant(ItemStack itemStack){
        List<String> lore = itemStack.getItemMeta().getLore();
        if (lore != null)
            for (int i = 0; i <lore.size(); i++) {
                String line = lore.get(i);
                if (line.contains(name))
                    return i;
            }
        return -1;

    }
    public boolean hasMinimumThisLevel(ItemStack itemStack){

        List<String> lore = itemStack.getItemMeta().getLore();
        if (findLineEnchant(itemStack)>=0){
            String line = lore.get(findLineEnchant(itemStack));
            System.out.println(line);
            if (LevelCustomEnchant.lineToInt(line) <= level.getLevel())
                return true;
        }
        return false;
    }
    public boolean hasThisLevel(ItemStack itemStack){
        List<String> lore = itemStack.getItemMeta().getLore();
        if (findLineEnchant(itemStack)>=0){
            String line = lore.get(findLineEnchant(itemStack));
                if (LevelCustomEnchant.lineToInt(line) == level.getLevel())
                    return true;
            }
        return false;
    }
    public void setEnchant(ItemStack itemStack){
        int loreLine = -1;
        System.out.println(hasThisEnchant(itemStack));
        System.out.println(hasMinimumThisLevel(itemStack));
        if (!itemStack.getType().equals(Material.AIR)) {
            if (hasThisEnchant(itemStack) && !hasThisLevel(itemStack))
                loreLine = findLineEnchant(itemStack);
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lore = meta.getLore() != null ? meta.getLore() : new ArrayList<>();
            String enchLine = "§7" + name + " " + level;
            if (loreLine >= 0)
                lore.set(loreLine, enchLine);
            else
                lore.add(enchLine);
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
            itemStack.addUnsafeEnchantment(Enchantment.LURE,0);
            meta = itemStack.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(meta);

        }
    }
}
