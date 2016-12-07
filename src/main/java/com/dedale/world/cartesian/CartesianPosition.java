package com.dedale.world.cartesian;

import java.util.Map;
import java.util.TreeMap;

import com.dedale.world.Position;

public class CartesianPosition implements Position {
    
    private static Map<Integer, Map<Integer, CartesianPosition>> positions = new TreeMap<>();
    
    final int x;
    final int y;
    
    public CartesianPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartesianPosition other = (CartesianPosition) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "{" + "x:" + x + ", " + "y:" + y + "}";
    }
    
    public static CartesianPosition of(int... coordinates) {
        return of(coordinates[0], coordinates[1]);
    }
    
    public static CartesianPosition of(int x, int y) {
        if (positions.get(x) == null) {
            positions.put(x, new TreeMap<>());
        }
        if (positions.get(x).get(y) == null) {
            positions.get(x).put(y, new CartesianPosition(x, y));
        }
        return positions.get(x).get(y);
    }
    
}
