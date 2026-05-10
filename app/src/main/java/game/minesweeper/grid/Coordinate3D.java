package game.minesweeper.grid;

public record Coordinate3D(int x, int y, int z) implements CoordinateInterface {

    @Override
    public boolean isFeasible() {
        return x >= 0 && y >= 0 && z >= 0;
    }

    @Override
    public String displayString() {
        return x + "," + y + "," + z;
    }
}
