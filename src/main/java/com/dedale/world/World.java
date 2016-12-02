package com.dedale.world;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface World {
    
    Collection<Location> getWorldMap();
    
    Location at(int... coordinates);
    
    Location at(Position position);
    
    void addLocation(Location location);
    
    Optional<Location> findLocation(Predicate<? super Location> matcher);
    
    Optional<Location> locationOf(Localizable localizable);
    
    // Position
    
    Position positionOf(Localizable localizable);
    
    Position translate(Position position, Orientation orientation);
    
}
