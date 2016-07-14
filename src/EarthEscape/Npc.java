package EarthEscape;

import java.util.ArrayList;

public abstract class Npc {

    public String name;
    public String conversationInstruction;
    public abstract void talkTo(ArrayList<String> sentence, Player player, Printer printer);
    
    
    public String getName() {
        return name;
    }
    
    public String getInstruction(){
        return this.conversationInstruction;
    }
}