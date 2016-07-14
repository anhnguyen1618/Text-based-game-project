package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Jacket extends Item {
    public Jacket(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);   
    }

    @Override
    public void use(Printer printer, Player player) {
        player.disguise();
        player.removeFromInv("jacket");
        printer.println("You look like a normal human now!", printer.DARK_CYAN);
    }
}
