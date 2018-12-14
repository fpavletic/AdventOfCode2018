package util;

import java.util.Objects;

public class Pair {

    private static int lastId = -1;

    public final int x, z;
    public final Integer id;

    public Pair(int x, int z){
        this.x = x;
        this.z = z;
        id = ++lastId;
    }

    public Pair(String coordinates){
        String[] coordinatesSplit = coordinates.split(", ");
        x = Integer.parseInt(coordinatesSplit[0]);
        z = Integer.parseInt(coordinatesSplit[1]);
        id = ++lastId;
    }

    public Pair(Pair parent, int direction){
        this(
                parent,
                parent.x + (direction % 2 == 0 ? direction - 1 : 0),
                parent.z + (direction % 2 != 0 ? direction - 2 : 0)
        );
    }

    public Pair(Pair parent, int x, int z){
        id = parent.id;
        this.x = x;
        this.z = z;
    }

    @Override
    public boolean equals(Object o){
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Pair pair = (Pair) o;
        return x == pair.x && z == pair.z;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, z);
    }

    @Override
    public String toString(){
        return String.format("Pair(%d): x = %d, y = %d", id, x, z);
    }
}
