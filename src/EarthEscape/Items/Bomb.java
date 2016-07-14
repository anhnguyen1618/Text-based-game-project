package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Bomb extends Item{
    public Bomb(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
        
    }
    
    @Override
    public void use(Printer printer, Player player) {
        if(player.invContains("bomb")){
            player.removeFromInv("bomb");
            printer.println("One part of the lab was blown up by your bomb, you can escape from this lab and go to the building by typing: north", printer.DARK_GREEN);
            player.getLocation().addExit("north", player.getRoom("building"));
            
            
        }else{
            printer.println("You do not have a bomb", printer.DARK_RED);
        }
        
    }
    
}
