package EarthEscape.Items;

import java.util.HashMap;
import EarthEscape.Item;
import EarthEscape.Player;
import EarthEscape.Printer;


public class Container extends Item {
    private HashMap<String,Item>List;
    public Container(String name, String floorDesc, String detailedDesc, boolean visible) {
        super(name, floorDesc, detailedDesc, visible); 
        List=new HashMap<>();
        
        
    }

    public void add(String itemName, Item item) {
        List.put(itemName, item);
    }

    
    public void remove(String itemName) {
        List.remove(itemName);
    }

    
    public Item get(String itemName) {
        Item item=List.get(itemName);
        return item;
    }

    
    public void printInventory(Printer printer) {
        if(this.List.isEmpty()){
            printer.println("It is empty now.", printer.DARK_RED);
            return;
        }
        printer.println("The " + this.itemName + " contains:", printer.DARK_RED);
        for(Item item: this.List.values()){
            printer.println(item.getItemName(), printer.DARK_BLUE);
            
        }
    }

    @Override
    public void use(Printer printer, Player player) {
        printer.println("How the hell can you use this",printer.DARK_RED);
    }
    @Override
    public void take(Player player, Printer printer) {
        printer.println("Nah, are you going to carry "+this.itemName+ " on your back? Who do you think you are?", printer.DARK_RED);
    }

    
    
    
}
