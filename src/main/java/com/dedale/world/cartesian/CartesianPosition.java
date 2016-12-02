package com.dedale.world.cartesian;

import java.util.Arrays;

import com.dedale.world.Position;

public class CartesianPosition implements Position {
    
    private static CartesianPosition[][] positions = new CartesianPosition[][] {};
    
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
        if (x >= positions.length) {
            positions = Arrays.copyOf(positions, x + 1);
            positions[x] = new CartesianPosition[] {};
        } else {
            if (positions[x] == null) {
                positions[x] = new CartesianPosition[] {};
            }
        }
        
        if (y >= positions[x].length) {
            positions[x] = Arrays.copyOf(positions[x], y + 1);
            positions[x][y] = new CartesianPosition(x, y);
        } else {
            if (positions[x][y] == null) {
                positions[x][y] = new CartesianPosition(x, y);
            }
        }
        
        return positions[x][y];
    }
    
}
