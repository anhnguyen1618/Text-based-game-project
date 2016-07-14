package EarthEscape;

import java.util.ArrayList;
import java.util.HashMap;

import EarthEscape.Items.Container;

public class Player {
    private Room location;
    private final Printer printer;
    private World world;
    private HashMap<String, Item> playerInventory= new HashMap<>();
    
    
    private String playerName;
    private boolean hasDirections;
    private boolean knowEnglish;
    private boolean disguise;
    private int maxItems;
    
    //Generate the player object and put him in the cage
    public Player(World world, Printer printer) {
        this.location = world.getRoomById("cage");
        this.world = world;
        this.printer = printer;
        this.knowEnglish=false;
        this.disguise=false;
        maxItems=0;
    }
   
    public void setName(String name) {
        this.playerName = name; 
    }
    
    public String getName() {
        return this.playerName;
    }
    
    public void changeTheLocation(String destination) {
        if (this.location.getDestination(destination) != null) {
            this.location=this.location.getDestination(destination);
            this.location.printRoom(printer);
            this.location.onEntrance(this, printer);
        } else printer.println("How do you expect to go there?", printer.DARK_RED);
    }
    
    public void teleport(String destination) {
            this.location = world.getRoomById(destination);
    }
    
    public Room getLocation() {
        return this.location;
    }
    
    public Room getRoom(String name){
       return world.getRoomById(name);
    }
    
    public boolean hasDirections(){
        return hasDirections;
    }
    
    public void giveDirections(){
        this.hasDirections = true;
    }
    
    public boolean knowEnglish(){
        return this.knowEnglish;
    }
    
    public void learnEnglish(){
        this.knowEnglish = true;
    }
    
    public boolean checkDisguise(){
        return this.disguise;
    }
    
    public void disguise(){
        this.disguise=true;
    }

    public void drop(String itemName) {
        if (invContains(itemName)) {
            Item currentItem = this.playerInventory.get(itemName);
            this.location.addToInventory(itemName, currentItem);
            removeFromInv(itemName);
            printer.println("You dropped " + currentItem.getItemName() + ".", printer.DARK_YELLOW);
            return;
        }
        printer.println("You do not have this item!", printer.DARK_RED);
    }
    
    public void takeFrom(String container, String itemToGet) { 
        if(this.location.getContainer(container) == null){
            printer.println("There is no " + container + " here!", printer.DARK_RED);
            return;
        }
        
        Item item=location.getContainer(container).get(itemToGet);
        if(item!=null){
            if(!inventoryIsFull()){
                addToInv(itemToGet, item);
                location.getContainer(container).remove(itemToGet);
                printer.println("You now have the " + item.getItemName() + "!", printer.DARK_BLUE);
            } else {
                printer.println("Your backpack is full, you will need to drop something first!", printer.DARK_RED);
            }  
        } else {
            printer.println("You don't see any " + itemToGet + " in the " + container + "!", printer.DARK_RED);
        }
    }
    
    public void examine(String itemName) {
        /* Check if the item is in the player inventory *
         * then if it is in the room                    */
        Item item;
        if(playerInventory.containsKey(itemName)) {            
            item = playerInventory.get(itemName);
        } else {
            item = this.location.getItemFromRoom(itemName, printer);
        }
        
        if (item != null)
            printer.println(item.getDetailedDesc(), printer.CLR_DEFAULT);
        else
            printer.println("There is no " + itemName + " here.", printer.DARK_RED);
    }
    
    
    public void say(ArrayList<String> wordsTold) {
        for (Npc npc : location.getNpcList().values()) {
            if (npc !=null) {
                npc.talkTo(wordsTold, this, printer);
            } else {
                printer.println("Are you talking to yourself again?", printer.DARK_RED);
            }
        }
    }
    
    public void tell(ArrayList<String> wordsTold, String target) {
        if (location.hasNpc(target)) {
            location.getNpc(target).talkTo(wordsTold, this, printer);
        } else {
            printer.println("Who exactly are you trying to talk to?", printer.DARK_RED);
        }   
    }
    
    /* Inventory related methods */
    
    public void listInventory() {
        if (this.playerInventory.isEmpty()) {
            printer.println("You are carrying nothing yet!", printer.DARK_YELLOW);
            return;
        }
        printer.println("You have:", printer.DARK_YELLOW);
        
        for (Item item : playerInventory.values()) {
            printer.println(item.getItemName(), printer.DARK_GREEN);
        }
    }
    
    public int getInvSize(){
        return this.maxItems;
    }
    
    public void setInvMax(int size){
        this.maxItems= size;
    }
    
    public void addToInv(String key, Item item) {
        this.playerInventory.put(key, item);
    }
    
    public void removeFromInv(String key) {
        this.playerInventory.remove(key);
    }
    
    public Item getFromInv(String key) {
        return this.playerInventory.get(key);
    }
    
    public boolean invContains(String itemName) {
        if (this.playerInventory.containsKey(itemName)){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean inventoryIsFull() {
        if (playerInventory.size() < maxItems) {
            return false;
        } else {
            return true;
        }
    }
}
