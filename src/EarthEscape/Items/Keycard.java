
package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Keycard extends Item {
    public Keycard(String name, String floorDesc, String detailedDesc, boolean visible){
        super(name, floorDesc, detailedDesc, visible);
    }
     @Override
    public void use(Printer printer, Player player) {
        if(player.getLocation().getId().equals("city")) { 
            player.removeFromInv("keycard");
            printer.println("The barcode scanner beeps, and the door into the garage opens. In order to get in the garage, please type: south",printer.DARK_RED);
            player.getLocation().addExit("south", player.getRoom("garage"));
            
        }else{
            printer.println("It has no use here",printer.DARK_RED);
        }
    
}
}

