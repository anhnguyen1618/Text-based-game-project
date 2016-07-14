package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Translator extends Item {
    public Translator(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
        
    }

    @Override
    public void use(Printer printer, Player player) {
        player.learnEnglish();
        printer.println("You can speak to humans!", printer.DARK_BLUE);
        player.removeFromInv("translator");
    }
    
}
