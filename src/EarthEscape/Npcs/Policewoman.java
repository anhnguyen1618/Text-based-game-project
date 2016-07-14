package EarthEscape.Npcs;

import java.util.ArrayList;
import EarthEscape.Npc;
import EarthEscape.Printer;

    public class Policewoman extends Npc {

        public Policewoman(String name) {
            this.name = name;
            this.conversationInstruction="Start a conversation with her by typing: say hello";
        }

        public void giveDirections(EarthEscape.Player player) {
            player.giveDirections();
        }

        @Override
        public void talkTo(ArrayList<String> sentence, EarthEscape.Player player, Printer printer) {
            if(player.knowEnglish()){
                if(sentence.contains("hello")){
                printer.println("The policewoman says: Sir I'm very busy, what do you need? To answer, please type: say <answer>.", printer.LIGHT_PURPLE);
                return;
                }
                if(sentence.contains("forest")){
                    player.giveDirections();
                    printer.println("The policewoman says: \"Take the train and get out at the 9th stop.\n To travel by train, you need a travel card\".", printer.DARK_YELLOW);               
                } else{
                    printer.println("I wish I could but I don't want to. If you need direction to some places, please say the name of the place", printer.CLR_DEFAULT);
                }               
            }else{
                printer.println("Since your language is weird, people capture you. Game Over, bro", printer.DARK_RED);
                System.exit(0);
            }
        }

    }

    
