package game.minesweeper;

public class Cell {

    private boolean revealed = false;
    private boolean hasMine = false;
    private boolean flagged = false;

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        revealed = true;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void placeMine() {
        hasMine = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void toggleFlag() {
        flagged = !flagged;
    }
}
