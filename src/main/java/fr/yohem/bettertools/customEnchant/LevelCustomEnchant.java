package fr.yohem.bettertools.customEnchant;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class LevelCustomEnchant {
    static Map<Character, Integer> translateMap = new HashMap<>();
    private int level;

    public LevelCustomEnchant(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    static public int lineToInt(String enchantLine){
        System.out.println(romanToInt(enchantLine.split(" ")[enchantLine.split(" ").length -1]));
        if (enchantLine.split(" ").length >= 0)
            return romanToInt(enchantLine.split(" ")[enchantLine.split(" ").length -1]);
        return 0;

    }

    @Override
    public String toString() {
        return getLevelString();
    }

    public String getLevelString(){
        String[] thousands = {"", "M", "MM", "MMM", "MMMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return level<5000?thousands[level / 1000] + hundreds[(level % 1000) / 100] + tens[(level % 100) / 10] + units[level % 10]:level+"";

    }
    private static int pairSmallNumberFirst(String pair) {
        initMap();
        if (translateMap.get(pair.charAt(0)) < translateMap.get(pair.charAt(1))) {
            if (translateMap.get(pair.charAt(1)) / translateMap.get(pair.charAt(0)) == 10 ||
                    translateMap.get(pair.charAt(1)) / translateMap.get(pair.charAt(0)) == 5) {
                return -1;
            } else {
                throw new IllegalArgumentException("Wrong number number sequence " + pair);
            }
        }
        return 1;
        //return translateMap.get(pair.charAt(0)) < translateMap.get(pair.charAt(1)) ? -1 : 1;
    }
    public  static int romanToInt(String romanNumber) {
        initMap();
        int result = 0;
        try {
            return Integer.parseInt(romanNumber);
        }catch (NumberFormatException e){
            while (romanNumber.length() > 1) {
                String pair = romanNumber.substring(0, 2);
                result += (translateMap.get(pair.charAt(1)) +
                        (translateMap.get(pair.charAt(0)) * pairSmallNumberFirst(pair)));
                romanNumber = romanNumber.replaceFirst(pair,"");
            }
            if (romanNumber.length() == 1) {
                result += translateMap.get(romanNumber.charAt(0));
            }
            return result;
        }
    }
    private static void initMap() {
        translateMap.put('I', 1);
        translateMap.put('V', 5);
        translateMap.put('X', 10);
        translateMap.put('L', 50);
        translateMap.put('C', 100);
        translateMap.put('D', 500);
        translateMap.put('M', 1000);
    }
}
