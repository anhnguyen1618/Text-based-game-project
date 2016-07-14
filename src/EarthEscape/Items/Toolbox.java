package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Toolbox extends Item{
    public Toolbox(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible); 
    }

    @Override
    public void use(Printer printer, Player player) {
        if(player.getLocation().getId().equals("forest")&&player.invContains("toolbox")){
            printer.println("Game over,bro", printer.DARK_RED);
            printer.println("Just, kidding... congratulation.The spaceship is now repaired. Let'go back to your planet", printer.DARK_BLUE);
            System.exit(0);
        }else{
            printer.println("It is useless here but may be you can use it somewhere else", printer.DARK_RED);
        }
    }
    
}
