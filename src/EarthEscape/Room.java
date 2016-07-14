package EarthEscape;

import java.util.HashMap;

import EarthEscape.Items.Container;

public class Room {
    private HashMap<String, Room> exits= new HashMap<>();
    private HashMap<String, Item> roomInventory = new HashMap<>();
    private HashMap<String, Container> containerList = new HashMap<>();
    private HashMap<String, Npc> npcList = new HashMap<>(); 
    
    private final String id;
    private final String roomName;
    private final String description;

    //Constructor//
    public Room(String id, String name, String description){
        this.id = id;
        this.roomName = name;
        this.description = description;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getName() {
            return this.roomName;
    }
        
    public String getDescription() {
            return this.description;
    }
        
    public void addExit(String s, Room l) {
        this.exits.put(s, l);
    }
    
    public Room getDestination(String destination){
        return exits.get(destination);
    }

    public void printNpcs(Printer printer) {
        for (Npc npc : this.npcList.values()) {
            printer.println(npc.getName() + " is here.", printer.CLR_DEFAULT);
            printer.println(npc.getInstruction(), printer.CLR_DEFAULT);
        }
    }
    
    public void printExit(Printer printer){
        //Print a list of exits below the room description (if any exist)
        if(exits.isEmpty()){
            return;
        }
        printer.print("Exits:", printer.DARK_BLUE);
        for(String exit: exits.keySet()) {
            printer.print(" [" + exit + "]", printer.DARK_BLUE);
        }
        printer.println("", printer.CLR_DEFAULT);
    }
    
    public void printRoom(Printer printer){
        //Print the room name, description, list of visible objects, npcs and exit.
        //Called when the player use 'look' command or enter a new room.
        printer.println(this.roomName, printer.DARK_RED);
        printer.println(this.description, printer.DARK_BLUE);
        this.printInventory(printer);
        this.printNpcs(printer);
        printExit(printer);
    }
    
    public void onEntrance(Player player, Printer printer) {
        //Events that might happens when a player enter a room.
        if(this.getId().equals("alley")){
            this.npcList.get("smoker").talkTo(null, player, printer);   
        }
        if(this.getId().equals("city")){
            if(!player.checkDisguise()){
                printer.println("Your strange appearance sends people screaming, and you are detained by the police. Game Over.", printer.DARK_RED);
                System.exit(0);
            }     
        }        
    }
    
    /* Inventory related methods */
    public void printInventory(Printer printer){
        for (Item item: this.roomInventory.values()) {
            if (item.isVisible())
                printer.println(item.getFloorDesc(), printer.CLR_DEFAULT);    
        }
    }
    
    
    public void addToInventory(String key, Item item) {
        this.roomInventory.put(key, item);
    }
    
    public void removeFromInv(String key) {
        this.roomInventory.remove(key);
    }
    
    public Item getFromInv(String key) {
        return this.roomInventory.get(key);
    }
    
    public void addContainer(String key, Container container) {
        this.containerList.put(key, container);
    }
    
    public Container getContainer(String key) {
        return this.containerList.get(key);
    }
    
    //if the room contains the item (if on the ground or in a container)
    public Item getItemFromRoom(String key, Printer printer) {
        if (containerList.containsKey(key)) {
            containerList.get(key).printInventory(printer);
            return containerList.get(key);
        }
        for (Container container : containerList.values()) {
            if (container.get(key) != null){
                return container.get(key);
            }
        }
        if (roomInventory.containsKey(key)) {
            return roomInventory.get(key);
        }
        return null;
    }
    
    public Container getContain(String key){
        for (Container container : containerList.values()) {
            if (container.get(key) != null){
                return container;
            }
        }
        return null;
    }
    
    public void addNpc(String key, Npc npc) {
        this.npcList.put(key, npc);
    }
    
    public boolean hasNpc(String key) {
        if (this.npcList.containsKey(key))
            return true;
        else
            return false;
    }
    
    public Npc getNpc(String key) {
        return this.npcList.get(key);
    }
    
    public HashMap<String, Npc> getNpcList() {
        return this.npcList;
    }
}
