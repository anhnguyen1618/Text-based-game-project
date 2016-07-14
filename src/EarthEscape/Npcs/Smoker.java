package EarthEscape.Npcs;

import EarthEscape.Npc;
import EarthEscape.Player;
import EarthEscape.Printer;
import java.util.ArrayList;

public class Smoker extends Npc {

    boolean hasCigarette = false;
    
    public Smoker(String name) {
        this.name = name;
        this.conversationInstruction="The smoker tips his hat at you.";
        
    }


    @Override
    public void talkTo(ArrayList<String> sentence, Player player, Printer printer) {
        
        if (player.knowEnglish()) {
        
            if (this.hasCigarette) {
               printer.println("The smoker says: \"Remember: to go downtown, go right, turn left, turn left again,  and turn right two times.\"", printer.DARK_GREEN);
            } else {
                printer.println("The smoker says: \"Hey buddy, if you want to go downtown I can give you directions if you have a cigarette.\"", printer.DARK_GREEN);
            }

            if (player.invContains("cigarette")){ 
                printer.println("The smoker says: \"Thanks for the smoke buddy!\"", printer.DARK_GREEN);
                printer.println("The smoker says: \"As promised, to go downtown, go right, turn left, turn left again,  and turn right two times.", printer.DARK_GREEN);
                this.hasCigarette = true;
                player.removeFromInv("cigarette");
            }
            if(!player.checkDisguise()){
                printer.println("The smoker says: \"Between you and me, do not go out in public with that face!\n"
                               +"You should try using a jacket or something to cover up.\"", printer.LIGHT_RED);
            } 
        } else {
            printer.println("The smoker says: \"Bur\", You will need to find a way to communicate with him.", printer.DARK_RED);        }
    }
        
}
