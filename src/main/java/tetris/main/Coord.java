package tetris.main;

public class Coord {
    public int x,y;

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Coord coord = (Coord) obj;
        if (coord.getX() == this.x && coord.getY() == this.y) {
            return true;
        }
        else {
            return false;
        }
    }

}
