package com.dedale.world.cartesian;

import java.util.function.Predicate;

import com.dedale.world.Location;
import com.dedale.world.Position;

class PositionLocator implements Predicate<Location> {
    
    private Position position;
    
    public PositionLocator(Position position) {
        this.position = position;
    }
    
    @Override
    public boolean test(Location location) {
        return location.getPosition().equals(position);
    }
    
}