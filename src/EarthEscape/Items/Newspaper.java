package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Newspaper extends Item {
    public Newspaper(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
        
    }

    @Override
    public void use(Printer printer, Player player) {
        if(player.knowEnglish()){
            printer.println("Now you know that your broken Aircraft is in the Epping forest. You have to ask other people about the location of the forest", printer.DARK_CYAN);
        }else{
            printer.println("dfsah43218 4ohifsda0qrfsa'fl;'asir", printer.DARK_RED);
        }
            
        
    }
    
}
