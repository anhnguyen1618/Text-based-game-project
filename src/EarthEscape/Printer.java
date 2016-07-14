package EarthEscape;

public class Printer {
    final public String DARK_RED =    "\033[0;31m";
    final public String DARK_GREEN =  "\033[0;32m";
    final public String DARK_YELLOW = "\033[0;33m";
    final public String DARK_BLUE =   "\033[0;34m";
    final public String DARK_PURPLE = "\033[0;35m";
    final public String DARK_CYAN =   "\033[0;36m";
    final public String DARK_WHITE =  "\033[0;37m";
    final public String LIGHT_BLACK = "\033[1;30m";
    final public String LIGHT_RED =   "\033[1;31m";
    final public String LIGHT_GREEN = "\033[1;32m";
    final public String LIGHT_YELLOW ="\033[1;33m";
    final public String LIGHT_BLUE =  "\033[1;34m";
    final public String LIGHT_PURPLE ="\033[1;35m";
    final public String LIGHT_CYAN =  "\033[1;36m";
    final public String LIGHT_WHITE = "\033[1;37m";
    final public String CLR_DEFAULT = "\033[0m";
    
    boolean colorOn;

    public Printer() {
        this.colorOn = true;
    }

    public void toggleColor() {
        if (this.colorOn == true) {
            this.colorOn = false;
            this.println("Colors are now: off.", DARK_RED);
        } else {
            this.colorOn = true;
            this.println("Colors are now: on.", DARK_BLUE);
        }
    }
    
    public void print(String text, String color) {
        if (this.colorOn) {
            System.out.print(color + text + CLR_DEFAULT);
        } else {
            System.out.print(text);
        }
    }
    
    public void println(String text, String color) {
        if (this.colorOn) {
            System.out.println(color + text + CLR_DEFAULT);
        } else {
            System.out.println(text);
        }
    }
    
    //For testing purpose
    public void colorTest() {
        this.println("DRED",        this.DARK_RED);
        this.println("DGREEN",      this.DARK_GREEN);
        this.println("DCYAN",       this.DARK_CYAN);
        this.println("DBLUE",       this.DARK_BLUE);
        this.println("DPURPLE",     this.DARK_PURPLE);
        this.println("DARKWHITE",   this.DARK_WHITE);
        this.println("DARKYELLOW",  this.DARK_YELLOW);
        this.println("DEFAULT",     this.CLR_DEFAULT);
        this.println("LIGHTBLACK",  this.LIGHT_BLACK);
        this.println("LIGHTBLUE",   this.LIGHT_BLUE);
        this.println("LIGHTCYAN",   this.LIGHT_CYAN);
        this.println("LIGHTGREEN",  this.LIGHT_GREEN);
        this.println("LIGHTPURPLE", this.LIGHT_PURPLE);
        this.println("LIGHTRED",    this.LIGHT_RED);
        this.println("LIGHTWHITE",  this.LIGHT_WHITE);
        this.println("LIGHTYELLOW", this.LIGHT_YELLOW);
    }
}
