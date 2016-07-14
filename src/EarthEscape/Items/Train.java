package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Train extends Item{
    
    public Train(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
    }
    
    @Override
    public void use(Printer printer, Player player) {
        printer.println("You cannot board the train now.", printer.DARK_RED);
    }
    
    @Override
    public void take(Player player, Printer printer) {
        if (player.invContains("travelcard")) {
            if(player.getLocation().getId().equals("station")){
                if(player.hasDirections()){
                    player.teleport("forest");
                    printer.println("One step closer to freedom.", printer.DARK_BLUE);
                    player.getLocation().printRoom(printer);
                } else{
                    printer.println("You don't know where to go, and get lost. The police find you. Enjoy Area 51. Game Over.", printer.DARK_RED);
                    System.exit(0);
                }
            }else if(player.getLocation().getId().equals("forest")){
                player.teleport("station");
                printer.println("You board the train back to the city.", printer.DARK_BLUE);
                player.getLocation().printRoom(printer);
            }           
        } else {
            printer.println("You get caught by the transport police. Perhaps you should have obeyed the law. Game Over.", printer.DARK_RED);
            System.exit(0);
        }
    }
}

   