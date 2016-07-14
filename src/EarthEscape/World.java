package EarthEscape;

import EarthEscape.Items.*;
import EarthEscape.Npcs.*;
import java.util.HashMap;

public class World {
    Room startingLocation;
    HashMap<String, Room> map = new HashMap<>();
    
    public World() {
        
        Room lab = new Room("laboratory", 
                            "Inside the lab", 
                            "You are in the middle of huge laboratory filled with accessories for the doctor's sinister plans. \n"
                           +"Now the scientist and the assistant are absent from the lab. There is a huge metal door on the \n"
                           +"north side of the room, but unfortunately it seems to be barred from the other side.\n"
                           +"You have to find a way to blow it up. Maybe with some explosive? If only you had the right chemicals...\n"
                           +"There is stuff laying in a [shelf], the [closet] is open and there is also a [table] in the corner.\n"
                           +"It could be a good idea to start by examining the things in there.");
        Room cage = new Room("cage", 
                             "Inside the cage", 
                             "A small, cramped cage. You can easily touch the sides. \nAttract someone's attention and do what they want to be released.");
        Room city = new Room("city", 
                             "In the city", 
                             "You have reached downtown. There are many pedestrians walking around, and a policewoman directing traffic.\n"
                            +"West from here you see the neighborhood you emerged from. To the south is a garage, but it seems to be closed at the moment.\n"
                            +"Right now the only thing that is opened is the train station, to the north.");
        Room forest = new Room("forest", 
                               "In the forest", 
                               "A big forest full of walking paths. Your ship is here, but it looks like you'll need something to get it working.\n"
                              +"You can't fix this Spaceship with your bare hands. In order to fix it, you have to find the toolbox inside the secret garage.\n"
                              +"One tip to find the garrage is trying using the keycard everywhere you go\n"
                              +"When you have a toolbox,you can use toolbox to fix your ship by typing: use toolbox.\n"
                              +"If you need to go back to the station, please take a train");
        Room building= new Room("building",
                                "An abandoned building in a shady part of town",
                                "There is a door north that is rusted cast iron. Unfortunately, the lock works, which means that you need a key to open it.\n"
                               +"Check your inventory to see if you have the key or not, if not, go back the the lab and take it");
        Room alley = new Room("alley", 
                              "In the alley", 
                              "A narrow, smelly alley.\nOnly someone shady would hang out here. \nStill, you'd better ask around about your ship.");
        Room garage = new Room("garage", 
                               "In the garage", 
                               "A dark, dirty garage. From the outside it's just another nondescript building.");
        Room station = new Room("station", 
                                "Inside the train station", 
                                "Thankfully the station is small and has only one exit. You can see the edge of the forest from here.\n"
                               +"Please take the train to travel to your wanted destination");
    
        Room m1 = new Room("the middle of a street",
                            "Somewhere in the city",
                            "You are wandering in the middle of a deserted street between large buildings. Why do humans enjoy living\n"
                           +"in depressing cubes like this? Walking here makes you wish you were back home already. Hopefully\n"
                           +"you will find your way out soon. You can hear the smoker coughing, west of here.");
        
        Room m2 = new Room("the middle of a street",
                            "Somewhere in the city",
                            "You are wandering in the middle of a deserted street between large buildings. Why do humans enjoy living\n"
                           +"in depressing cubes like this? Walking here makes you wish you were back home already. Hopefully\n"
                           +"you will find your way out soon.");        
        Room m3 = new Room("the middle of a street",
                            "Somewhere in the city",
                            "You are wandering in the middle of a deserted street between large buildings. Why do humans enjoy living\n"
                           +"in depressing cubes like this? Walking here makes you wish you were back home already. Hopefully\n"
                           +"you will find your way out soon.");        
        Room m4 = new Room("the middle of a street",
                            "Somewhere in the city",
                            "You are wandering in the middle of a deserted street between large buildings. Why do humans enjoy living\n"
                           +"in depressing cubes like this? Walking here makes you wish you were back home already. Hopefully\n"
                           +"you will find your way out soon.");
      
    /*for testing purpose
    cage.addExit("north",    lab);
    lab.addExit("north",     building);
    lab.addExit("south",     cage);
    building.addExit("north", alley);
    city.addExit("south",    garage);
    station.addExit("north", forest);
    forest.addExit("south",  station);
    station.addExit("north", forest);
    */
        
    /*Game exits*/
    building.addExit("south", lab);
    alley.addExit("south",   building);
    alley.addExit("east",    m1);
    city.addExit("north",    station);
    city.addExit("west",     m4);
    garage.addExit("north",  city);
    station.addExit("south", city);
        
    //MAZE - east, north(m1), west(m2), north(m3), east(to the city)
    m1.addExit("north", m2);
    m1.addExit("south", m1);
    m1.addExit("east", m1);
    m1.addExit("west", alley);
    m2.addExit("north", m1);
    m2.addExit("south", m1);
    m2.addExit("east", m1);
    m2.addExit("west", m3);
    m3.addExit("north", m4);
    m3.addExit("south", m1);
    m3.addExit("east", m1);
    m3.addExit("west", m1);
    m4.addExit("north", m1);
    m4.addExit("south", m1);
    m4.addExit("east", city);
    m4.addExit("west", m1);     
    
        /**********************************************************************
         *                              ITEMS                                 *
         *  1st field: ID/name                                                *
         *  2nd field: What is displayed in the room "inventory"              *
         *  3rd field: description (examine, look at)                         *
         *  4th: If it is to be displayed with the room description           *
         *                                                                    *
         *********************************************************************/
        
        UselessItem H2SO4 = new UselessItem(  "h2so4", 
                            "There is a bottle of H2SO4.",
                            "The major use of sulfuric acid is in the production of fertilizers,"
                           +" e.g., superphosphate of lime and ammonium sulfate.", 
                            true);
        
        Hno3 HNO3 = new Hno3("hno3", 
                            "There is a bottle of HNO3.",
                            "It can be used to make a TNT bomb.", 
                            true);
       
        C6h5ch3 C6H5CH3 = new C6h5ch3(  "c6h5ch3", 
                                        "There is a bottle of C6H5CH3.",
                                        "It can be used to make a bomb.", 
                                        true);
        Key key = new Key("key", 
                          "There is a key.",
                          "It can be used to open the door of the building.", 
                          true);
        
        Keycard keycard = new Keycard("keycard", 
                                      "Someone has dropped a keycard here.",
                                      "It matches the reader on the garage door.", 
                                      true); 
        
        Translator translator = new Translator("translator", 
                                               "You found your translator!",
                                               "Using it will allows you to communicate with humans.", 
                                               true);
        
        UselessItem cigarette = new UselessItem("cigarette", 
                                                "There is a cigarette lying there.",
                                                "Maybe there is someone around here who is looking for this...", 
                                                true);
        UselessItem travelCard = new UselessItem("travelcard", 
                                                 "There is a travelcard.",
                                                 "It can be used to take the train.", 
                                                 true);
        UselessItem gun = new UselessItem ("gun", 
                                           "There is a gun.",
                                           "Oh... It's only a water gun. Not recommended for self-defense.", 
                                           true);
        UselessItem raincoat = new UselessItem  ("raincoat", 
                                                 "There is a raincoat.",
                                                 "It can be used to protect your body from water gun toy.", 
                                                 true);
        UselessItem calculator = new UselessItem("calculator", 
                                                 "There is a calculator.",
                                                 "It can be used to calculate the distance between Earth and Moon.", 
                                                 true);
        
        UntakableItem note = new UntakableItem ("note",
                                                "There is a note laying on the shelf, begging for you to examine it.",
                                                "It reads: \n-Chemistry for dummies-\n"
                                               +"Doing science is easy! You just need to take chemicals and combine them together.\n"
                                               +"combine <first item> with <second item>",
                                                true);
        Jacket jacket = new Jacket ("jacket", 
                                    "There is a large wooly jacket.",
                                    "The collar will hide your face! Maybe the mad doctor left something in the pockets?", 
                                    true);
        Newspaper newspaper = new Newspaper("newspaper", 
                                            "There is a newspaper.",
                                            "It's always a good idea to learn about the world. To read this newspaper, please take it and use it.", 
                                            true);
        Toolbox toolbox = new Toolbox("toolbox", 
                                      "There is toolbox.",
                                      "Perhaps it would be useful for repairing something?", 
                                      true);
        Train train = new Train ("train", 
                                 "There is an A train waiting here. It will leave in 1 mins. Hurry up!", 
                                 "The train can be used to travel to infinity and beyond.", true);
        
        /********************************
         *  Containers: see items above *
         *******************************/
        Container table = new Container("table", 
                                        "There is a table near on your left.",
                                        "You can take items from table by typing: take itemName from table", 
                                        false);
        Container shelf = new Container("shelf", 
                                        "There is a shelf next to a table.",
                                        "You can take items from shelf by typing: take itemName from shelf", 
                                        false);
        Container closet = new Container("Closet", 
                                        "There is a closet next to the shelf.",
                                        "You can take items from closet by typing: take itemName from closet", 
                                        false);
        
        
        //add small item into container
        
        shelf.add("h2so4",      H2SO4);
        shelf.add("hno3",       HNO3);
        shelf.add("c6h5ch3",    C6H5CH3);
        table.add("key",        key);
        table.add("translator", translator);
        table.add("gun",        gun);
        table.add("calculator", calculator);
        closet.add("travelcard",travelCard);
        table.add("cigarette",  cigarette);
        closet.add("jacket",    jacket);  
        closet.add("raincoat",  raincoat);
        
        /***********************
         *      Place Items    *
         **********************/
        
        lab.addContainer("shelf",        shelf);
        lab.addToInventory("shelf",      shelf);
        lab.addContainer("table",        table);
        lab.addToInventory("table",      table);
        lab.addContainer("closet",       closet);
        lab.addToInventory("closet",     closet);
        lab.addToInventory("note",       note);
        city.addToInventory("newspaper", newspaper);
        station.addToInventory("train",  train);
        station.addToInventory("keycard",keycard);
        garage.addToInventory("toolbox", toolbox);
        forest.addToInventory("train",   train);
        
        /*********************
         *    Add NPC        *
         ********************/
        Scientist scientist =       new Scientist("The Scientist");
        Policewoman policewoman =   new Policewoman("A policewoman");
        Smoker smoker =             new Smoker("A smoker");
        Assistant assistant =       new Assistant("The assistant");
        
        cage.addNpc("scientist",   scientist);
        alley.addNpc("smoker",     smoker);
        city.addNpc("policewoman", policewoman);
        cage.addNpc("assistant",   assistant);
        
        //We can use the displayed id for this game, unless the id isn't unique (ex, maze)
       
        map.put(cage.getId(),    cage);
        map.put(lab.getId(),     lab);
        map.put(building.getId(),building);
        map.put(alley.getId(),   alley);
        map.put(city.getId(),    city);
        map.put(garage.getId(),  garage);
        map.put(station.getId(), station);
        map.put(forest.getId(),  forest);
        
        map.put("m1", m1);
        map.put("m2", m2);
        map.put("m3", m3);
        map.put("m4", m4);
    }
    
    public Room getRoomById(String roomId) {
        return map.get(roomId);
    }
}
