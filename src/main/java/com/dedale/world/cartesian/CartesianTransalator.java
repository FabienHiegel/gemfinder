package com.dedale.world.cartesian;

import com.dedale.world.Orientation;
import com.dedale.world.Position;

public class CartesianTransalator {
    
    public Position translate(Position position, Orientation orientation) {
        if (!(position instanceof CartesianPosition && orientation instanceof CartesianOrientation)) {
            return position;
        }
        CartesianPosition cartesianPosition = (CartesianPosition) position;
        CartesianOrientation cartesianOrientation = (CartesianOrientation) orientation;
        switch (cartesianOrientation) {
            case NORTH:
                return CartesianPosition.of(cartesianPosition.x, cartesianPosition.y + 1);
            case WEST:
                return CartesianPosition.of(cartesianPosition.x - 1, cartesianPosition.y);
            case SOUTH:
                return CartesianPosition.of(cartesianPosition.x, cartesianPosition.y - 1);
            case EAST:
                return CartesianPosition.of(cartesianPosition.x + 1, cartesianPosition.y);
            default:
                return position;
        }
    }
    
}
