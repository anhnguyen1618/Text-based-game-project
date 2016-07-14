package EarthEscape.Items;

import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;

public class C6h5ch3 extends Item{
    public C6h5ch3(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible);
        
    }
    
    @Override
    public void combine(String secondItem, Player player, Printer printer) {
        if (secondItem.equals("hno3") && player.invContains("hno3")) {
            player.removeFromInv("hno3");
            player.removeFromInv("c6h5ch3");            
            Bomb bomb=new Bomb("a bomb",
                               "The bomb is sitting on the floor, waiting for you to blow it up",
                               "It can be used to blow up the door",
                               true);
            player.addToInv("bomb", bomb);
            printer.println("Now you have a TNT bomb. Let's make it explode by typing command: use bomb", printer.DARK_RED);
        } else {
            printer.println("Well, at least you have half the ingredients right... You're almost there.", printer.DARK_RED);
        }
    }
    
    @Override
    public void use(Printer printer, Player player) {
        printer.println("You better be careful handling this...", printer.DARK_RED);
    }
}