package fr.yohem.bettertools;
import fr.yohem.bettertools.customEnchant.CustomEnchant;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToolsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length >= 1){
            int level;
            if (args.length >=2){
                try {
                    level = Integer.parseInt(args[args.length-1]);
                }catch (NumberFormatException e){
                    level = 0;
                }
            }else
                level = 0;
            String[] wordInEnch = new String[args.length-1];
            System.arraycopy(args, 0, wordInEnch, 0,args.length-1);
            String enchantName = String.join(" ", wordInEnch);
            if (sender instanceof Player){
                Player player = ((Player) sender);
                ItemStack inHand =player.getInventory().getItemInMainHand();
                if (!inHand.getType().equals(Material.AIR)){
                    new CustomEnchant(enchantName, level).setEnchant(inHand);
                }
            }else
                sender.sendMessage("l'utilisateur de la commande doit etre un joueur");
        }else
            sender.sendMessage("Format requit : /customenchant <enchant name> <level>");
        return false;
    }
}
