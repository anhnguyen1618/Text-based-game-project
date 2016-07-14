package EarthEscape.Npcs;

import java.util.ArrayList;
import EarthEscape.Npc;
import EarthEscape.Printer;

public class Scientist extends Npc {
        
    public Scientist(String name){
            this.name = name;
            this.conversationInstruction="Start a conversation with him by typing: \"say hello\" ";
   }
    
    
    @Override 
       public void talkTo(ArrayList<String> sentence, EarthEscape.Player player, Printer printer){
          if(sentence.contains("help")||sentence.contains("touch")){
              printer.println("The scientist glares, but says nothing.", printer.CLR_DEFAULT);
              return;
          }
          if(sentence.contains("hello")){
              printer.println("The scientist stares at you and says:\n \"I'll let you out, if you can prove you understand logic.\n Giraffe is to tall as mouse is to ___? Please type: say [Your answer] to answer this rhyming riddle.\"", printer.CLR_DEFAULT);
              return;
          }
          if(sentence.contains("small")){
              player.teleport("laboratory");
              printer.println("He says: \"At least a basic understand then. Fine, you may stay in the laboratory\"", printer.CLR_DEFAULT);
              player.getLocation().printRoom(printer);
             
          } else {
              printer.println("The scientist goes back to his work. Wrong answer. ", printer.CLR_DEFAULT);
          }
    }
}