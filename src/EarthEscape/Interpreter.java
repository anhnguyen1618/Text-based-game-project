package EarthEscape;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BooleanSupplier;

public class Interpreter {
    
    Player player;
    Scanner reader;
    String input;
    World world;
    Printer printer;
    
     /******************************************
     *               CONSTANTS                 *
     ******************************************/
    //Words to be cropped from the player's input
    final List<String> wordsToCrop = Arrays.asList("the", "at", "and", "with", "and");
    //list of valid prepositions
    final List<String> prepositions = Arrays.asList("in", "inside", "from");
    //List of commands (to be displayed on "help" or "commands")
    final List<String> validCommands = Arrays.asList("take <>", "take <> from <>", "drop <>", "use <>", "combine <> <>", "look", "look in <>", "examine <>",
                                                      "say <sentence>", "tell <name>", "inventory", "color (to toggle)", "north", "south", "east", "west");
    
    
     /******************************************
     *                CONSTRUCTOR              *   
     ******************************************/
    public Interpreter(World world) {
        this.world = world;
        this.printer = new Printer();
        this.player = new Player(world, printer);
        this.reader = new Scanner(System.in);
    }
    
    /*******************************************
    *                 GAME LOOP                *
    *******************************************/
    public void startGame() {
        draw();
        setName();
        setDifficulty();
        printer.println("", printer.CLR_DEFAULT); //blankline
        player.getLocation().printRoom(printer);
        
        loop : while (true) {
            printer.println("", printer.CLR_DEFAULT);
            printPrompt();
            //take input, and pass an array with the words converted to lowercase
            
            
            parse(reader.nextLine().toLowerCase().split(" +"));   
        }
    }
    
    private void parse(String[] input) { 
    /*  Input processing: take the whole input, crop unnecessary words then pass:
                String verb - (first word)
                String preposition (if any)
                ArrayList of nouns (all the other words)
                ArrayList of the whole input (to send all the input, for "tell" and "say")
        to the "action processor". */
    
        ArrayList<String> nouns = new ArrayList<>();
        ArrayList<String> wholeInput = new ArrayList<>();
        String verb = input[0];
        String preposition = "";
        
        //ask for a new input if the input is empty, return to game loop.
        if (verb.equals("")) {
            printer.println("Try to type something next time!", printer.DARK_RED);
            return;
        }
        
        //Crop unneccessary words & separate preposition if present
        if (input.length > 1) {
            for (int i = 1; i<input.length;i++) {
                wholeInput.add(input[i]);
                if (!wordsToCrop.contains(input[i])) {
                    if (prepositions.contains(input[i])) {
                        preposition = input[i];
                    } else {
                        nouns.add(input[i]);
                    }
                }
            }
        }
        processAction(verb, preposition, nouns, wholeInput);
    }
    
    private void processAction(String verb, String preposition, ArrayList<String> nouns, ArrayList<String> wholeInput) {
 
        HashMap<String, Runnable> commands = new HashMap<>();
        final String newVerb = verb + preposition; //combine verb and preposition for "compound commands"
        
        printer.println("", printer.CLR_DEFAULT); //skip a line before next output for clarity
        
        commands.put("n",           () -> player.changeTheLocation("north"));
        commands.put("s",           () -> player.changeTheLocation("south"));
        commands.put("e",           () -> player.changeTheLocation("east"));
        commands.put("w",           () -> player.changeTheLocation("west"));
        commands.put("north",       () -> player.changeTheLocation(verb));
        commands.put("south",       () -> player.changeTheLocation(verb));
        commands.put("east",        () -> player.changeTheLocation(verb));
        commands.put("west",        () -> player.changeTheLocation(verb));
        
        commands.put("inventory",   () -> player.listInventory());
        commands.put("inv",         () -> player.listInventory());
        commands.put("i",           () -> player.listInventory());
        
        commands.put("quit",        () -> confirmExit());
        commands.put("exit",        () -> confirmExit());
        
        commands.put("color",       () -> printer.toggleColor());
        //commands.put("colortest",   () -> printer.colorTest());
        
        commands.put("commands",    () -> listCommands());
        commands.put("command",     () -> listCommands());   
        commands.put("help",        () -> listCommands());
        

        /*  Input validation for commands with parameters:
                1-Condition
                2-What to run when valid
                3-What to output if invalid
        */                                     
        commands.put("examine",  makeCommand(() -> !nouns.isEmpty(),
                                             () -> player.examine(nouns.get(0)),
                                             () -> printer.println("Examine what?", printer.DARK_RED)));
        commands.put("lookat",   makeCommand(() -> !nouns.isEmpty(),
                                             () -> player.examine(nouns.get(0)),
                                             () -> printer.println("Look at what?", printer.DARK_RED)));
        commands.put("look",     makeCommand(() -> nouns.isEmpty(),
                                             () -> player.getLocation().printRoom(printer),
                                             () -> printer.println("Maybe you should try \"look inside\" or \"examine\"?", printer.DARK_RED)));                                     
        commands.put("lookin",   makeCommand(() -> !nouns.isEmpty() &&
                                                   (player.getLocation().getContainer(nouns.get(0)) != null),
                                             () -> player.getLocation().getContainer(nouns.get(0)).printInventory(printer),
                                             () -> printer.println("You do not see that here!", printer.DARK_RED)));
        commands.put("lookinside",makeCommand(()-> !nouns.isEmpty() &&
                                                   (player.getLocation().getContainer(nouns.get(0)) != null),
                                             () -> player.getLocation().getContainer(nouns.get(0)).printInventory(printer),
                                             () -> printer.println("You do not see that here!", printer.DARK_RED)));
        commands.put("say",      makeCommand(() -> !wholeInput.isEmpty(),
                                             () -> player.say(wholeInput),
                                             () -> printer.println("Say what?", printer.DARK_RED)));
        commands.put("tell",     makeCommand(() -> wholeInput.size() > 1,
                                             () -> player.tell(wholeInput, nouns.get(0)),
                                             () -> printer.println("Tell what to who?", printer.DARK_RED)));
        commands.put("take",     makeCommand(() -> !nouns.isEmpty() && 
                                                   (player.getLocation().getItemFromRoom(nouns.get(0), printer) != null),
                                             () -> player.getLocation().getItemFromRoom(nouns.get(0), printer).take(player, printer),
                                             () -> printer.println("Take what?", printer.DARK_RED)));    
        commands.put("drop",     makeCommand(() -> !nouns.isEmpty(),
                                             () -> player.drop(nouns.get(0)),
                                             () -> printer.println("Drop what?", printer.DARK_RED)));          
        commands.put("takefrom", makeCommand(() -> nouns.size()>=2,
                                             () -> player.takeFrom(nouns.get(1), nouns.get(0)),
                                             () -> printer.println("Take what from what?", printer.DARK_RED)));    
        commands.put("combine",  makeCommand(() -> nouns.size()>=2 && player.invContains(nouns.get(0)),
                                             () -> player.getFromInv(nouns.get(0)).combine(nouns.get(1), player, printer),
                                             () -> printer.println("Combine what with what? (Items must be in inventory)", printer.DARK_RED)));   
        commands.put("use",      makeCommand(() -> !nouns.isEmpty() && player.invContains(nouns.get(0)),
                                             () -> player.getFromInv(nouns.get(0)).use(printer, player),
                                             () -> printer.println("You are not carrying this!", printer.DARK_RED)));
        
        if (commands.containsKey(newVerb))
            commands.get(newVerb).run(); 
        else
            printer.println("I don't understand this command, type [help] to get a list!", printer.DARK_RED);
    }                               
    
    public Runnable makeCommand(BooleanSupplier condition, Runnable action, Runnable invalidAction) {
        //For actions with parameters. Execute action if the conditions are met, display error message if not.
        return () -> {
            if (condition.getAsBoolean())
                action.run();
            else 
                invalidAction.run();
        };
    }
    ////////////////////////////////////////////////////////////////////////////
    //From now on, methods to ask/set name & difficulty, and printing output  //
    //which didn't belong anywhere...                                         //
    ////////////////////////////////////////////////////////////////////////////
    private void setName() {
        while (true) {
            printer.print("Enter your name: ", printer.CLR_DEFAULT);
            String name = reader.nextLine();
            if (name.length()>1) {
                player.setName(name);
                break;
            }
            printer.println("Your name is too short!", printer.DARK_RED);   
        }
    }
    
    private void setDifficulty(){
        while (true) {
            printer.print("Please choose difficulty level(hard or easy): ", printer.CLR_DEFAULT);
            String level = reader.nextLine();
            if (level.equals("hard")) {
                player.setInvMax(7);
                break;
            }else if(level.equals("easy")){
                player.setInvMax(10);
                break;
            }
            printer.println("Please chose the difficulty level!", printer.DARK_RED);
            
        }
    }
    
    private void printPrompt() {
        printer.print(player.getName() +", in the " + player.getLocation().getId() + ">> ", printer.CLR_DEFAULT);
    }
    
    private void confirmExit() {
        printer.print("Please enter \"y\" to confirm exit: ", printer.DARK_RED);
        String playerInput = reader.nextLine().toLowerCase();
        if (playerInput.equals("yes") || playerInput.equals("y")) {
            System.exit(0);
            
        }
    }
    
    private void listCommands() {
        //Display the list of commands from the validCommands List, 3 per row, with padding and separated by "|"
            String delimiter = "|  ";
            printer.print("***************************", printer.DARK_RED);
            printer.print(" VALID COMMANDS ", printer.DARK_BLUE);
            printer.println("***************************", printer.DARK_RED);
            printer.print(delimiter , printer.DARK_RED);
            
        for (int i=0, j=1; i < validCommands.size(); i++) {
            printer.print(String.format("%1$-20s", validCommands.get(i)), printer.DARK_BLUE);
            if (j==3) {
                printer.println("|", printer.DARK_RED);
                if (validCommands.size() > i+1) {
                    printer.print(delimiter, printer.DARK_RED);
                    j=1;
                }
            } else {
                printer.print(delimiter, printer.DARK_RED);
                j++;
            }
       }
    }
    
    private void draw(){
        String drawing="     .     .       .  .   . .   .   . .    +  .\n" +
                    "       .     .  :     .    .. :. .___---------___. \n" +
                    "            .  .   .    .  :.:. _\".^ .^ ^.  '.. :\"-_. . \n" +
                    "         .  :       .  .  .:../:            . .^  :.:\\. \n" +
                    "             .   . :: +. :.:/: .   .    .        . . .:\\ \n" +
                    "      .  :    .     . _ :::/:               .  ^ .  . .:\\ \n" +
                    "       .. . .   . - : :.:./.                        .  .:\\ \n" +
                    "       .      .     . :..|:                    .  .  ^. .:| \n" +
                    "         .       . : : ..||        .                . . !:| \n" +
                    "       .     . . . ::. ::\\(                           . :)/ \n" +
                    "      .   .     : . : .:.|. ######              .#######::| \n" +
                    "       :.. .  :-  : .:  ::|.#######           ..########:| \n" +
                    "      .  .  .  ..  .  .. :\\ ########          :######## :/ \n" +
                    "       .        .+ :: : -.:\\ ########       . ########.:/\n" +
                    "         .  .+   . . . . :.:\\. #######       #######..:/\n" +
                    "           :: . . . . ::.:..:.\\           .   .   ..:/ \n" +
                    "        .   .   .  .. :  -::::.\\.       | |     . .:/ \n" +
                    "           .  :  .  .  .-:.\":.::.\\             ..:/ \n" +
                    "      .      -.   . . . .: .:::.:.\\.           .:/ \n" +
                    "     .   .   .  :      : ....::_:..:\\   ___.  :/ \n" +
                    "        .   .  .   .:. .. .  .: :.:.:\\       :/ \n" +
                    "          +   .   .   : . ::. :.:. .:.|\\  .:/| \n" +
                    "          .         +   .  .  ...:: ..|  --.:|\n" +
                    "     .      . . .   .  .  . ... :..:..\"(  ..)\" \n" +
                    "      .   .       .      :  .   .: ::/  .  .::\\ \n";
        
        String logo="███████╗ █████╗ ██████╗ ████████╗██╗  ██╗    ███████╗███████╗ ██████╗ █████╗ ██████╗ ███████╗\n" +
                    "██╔════╝██╔══██╗██╔══██╗╚══██╔══╝██║  ██║    ██╔════╝██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝\n" +
                    "█████╗  ███████║██████╔╝   ██║   ███████║    █████╗  ███████╗██║     ███████║██████╔╝█████╗  \n" +
                    "██╔══╝  ██╔══██║██╔══██╗   ██║   ██╔══██║    ██╔══╝  ╚════██║██║     ██╔══██║██╔═══╝ ██╔══╝  \n" +
                    "███████╗██║  ██║██║  ██║   ██║   ██║  ██║    ███████╗███████║╚██████╗██║  ██║██║     ███████╗\n" +
                    "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝    ╚══════╝╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝     ╚══════╝\n";
                
        String line="---------------------------------------------------------------------------------------------\n"+
                    "---------------------------------------------------------------------------------------------\n";            
                
                
        printer.print(drawing, printer.DARK_BLUE);
        printer.print(line, printer.DARK_BLUE);
        printer.println(logo, printer.DARK_BLUE);
        printer.print(line, printer.DARK_BLUE);
        String storyLine="You are an Alien from Xanadu.One day, you receives a signal from a far away planet called \n"+
                "Upon landing, you are captured by one mad scientist and his assistant\n"+
                "you have to escape from this evil planet. Let's go home.";
        printer.println(storyLine, printer.DARK_BLUE);
        printer.println("Type \"help\" or \"commands\" at any time for a list of valid commands.\n"
                       +"You can toggle the colors on and off by typing \"color\"\n"
                       +"You can see what you are carrying by typing\"inventory\"\n, "
                       +"take items, use them, even combine them! Speak to people and try to get help from them.\n"
                       +"Explore!", printer.DARK_RED);
    }
}

