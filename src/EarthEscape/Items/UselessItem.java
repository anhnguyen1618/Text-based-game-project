package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class UselessItem extends Item {
    public UselessItem (String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
    }
    
    @Override
    public void use(Printer printer, Player player) {
        printer.println("You can't use this!", printer.DARK_RED);
    }
}