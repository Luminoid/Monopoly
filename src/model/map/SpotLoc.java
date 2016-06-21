package model.map;

/**
 * Created by Ethan on 16/4/29.
 */
public class SpotLoc {
    private int x;
    private int y;

    public SpotLoc(int x, int y) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotLoc spotLoc = (SpotLoc) o;

        if (x != spotLoc.x) return false;
        return y == spotLoc.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
