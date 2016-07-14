package EarthEscape.Npcs;

import java.util.ArrayList;
import EarthEscape.Npc;
import EarthEscape.Player;
import EarthEscape.Printer;

public class Assistant extends Npc {
        
    public Assistant(String name){
            this.name = name;
            this.conversationInstruction="Start a conversation with her by typing: \"say help\" ";
   }
    public void help(Player player){
        player.teleport("city");
    }
    
    @Override 
       public void talkTo(ArrayList<String> sentence, EarthEscape.Player player, Printer printer){
          if(sentence.contains("hello")||(sentence.contains("small"))){
              return;
          }
          if(sentence.contains("help")){
              printer.println("The assistant looks at you and says:\n \"I can't understand you... Can you signal what you want somehow? \n Please type: say [Your gesture] to answer this question \"\nThe gesture is t*uch", printer.CLR_DEFAULT);
              return;
          }
          if(sentence.contains("touch")){
              player.teleport("laboratory");
              printer.println("The assistant says: \"I see! It's too small!\"", printer.CLR_DEFAULT);
              player.getLocation().printRoom(printer);
          } else{
              printer.println("The assistant says: \"That's not the gesture I'm looking for.\" (Hint: examine the cage.)", printer.CLR_DEFAULT);
          }
        
    }
}