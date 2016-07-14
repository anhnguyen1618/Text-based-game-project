package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class UntakableItem extends Item{
    public UntakableItem (String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
        
    }
    @Override
    public void use(Printer printer, Player player) {
        printer.println("You can't use this!", printer.DARK_RED);
    }
    @Override
    public void take(Player player, Printer printer) { 
        printer.println("Just leave it there, you don't need this.", printer.DARK_RED);
    }
}