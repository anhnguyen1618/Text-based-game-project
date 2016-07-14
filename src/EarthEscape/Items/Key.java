package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class  Key extends Item {
    public Key(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
       
    }

    @Override
    public void use(Printer printer, Player player) {
        if(player.getLocation().getId().equals("building")) { 
            player.removeFromInv("key");
            printer.println("You had just opened the door, now you can go to the alley",printer.DARK_RED);
            player.getLocation().addExit("north", player.getRoom("alley"));
            
        }else{
            printer.println("It has no use here",printer.DARK_RED);
        }
    }
}
