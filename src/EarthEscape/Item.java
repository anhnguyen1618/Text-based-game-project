package EarthEscape;

public abstract class Item {
    protected String itemName;
    protected String floorDesc;
    protected String detailedDesc;
    protected boolean visible;

    
    
    public Item(String name, String floorDesc, String detailedDesc, boolean visible) {
        this.itemName = name;
        this.floorDesc = floorDesc;
        this.detailedDesc = detailedDesc;
        this.visible = visible;
    } 
    
    public String getItemName() {
        return this.itemName;
    }

    public String getFloorDesc() {
        return this.floorDesc;
    }
    
    public String getDetailedDesc() {
        return this.detailedDesc;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void combine(String secondItem, Player player, Printer printer) {
        //Override this if combination is possible
        if (player.invContains(secondItem)) {
            printer.println("You put the " + this.itemName + " on the " + secondItem + " and it does... nothing.", printer.DARK_RED);
        } else {
            printer.println("You have no " + secondItem + " on you!", printer.DARK_RED);
        }
    }
    
    public void take(Player player, Printer printer) {
        if(!player.inventoryIsFull()) {
                if(player.getLocation().getFromInv(itemName)!=null){
                    player.addToInv(itemName, player.getLocation().getFromInv(itemName));
                    player.getLocation().removeFromInv(itemName);
                    printer.println("You now have "+itemName, printer.DARK_BLUE);
                }else if(player.getLocation().getContain(itemName)!= null){
                    player.addToInv(itemName, player.getLocation().getItemFromRoom(itemName, printer));
                    player.getLocation().getContain(itemName).remove(itemName);
                    printer.println("You now have "+itemName, printer.DARK_BLUE);
                }
            } else {
                printer.println("Your backpack is full, you will need to drop something first!", itemName);
            }
    }
    
    public abstract void use(Printer printer, Player player);
}