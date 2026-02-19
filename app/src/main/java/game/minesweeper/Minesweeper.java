package game.minesweeper;

public class Minesweeper {

    public String getGreeting() {
        return "Welcome to Minesweeper!! This is the entry point";
    }

    public static void main(String[] args) {
        System.out.println(new Minesweeper().getGreeting());
    }

}
