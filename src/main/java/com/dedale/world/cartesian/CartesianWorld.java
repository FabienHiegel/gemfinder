package com.dedale.world.cartesian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import com.dedale.world.Localizable;
import com.dedale.world.Location;
import com.dedale.world.Orientation;
import com.dedale.world.Position;
import com.dedale.world.World;

public class CartesianWorld implements World {
    private Collection<Location> worldMap = new ArrayList<>();
    private CartesianTranslator translator = new CartesianTranslator();
    
    public Collection<Location> getWorldMap() {
        return Collections.unmodifiableCollection(worldMap);
    }
    
    // Location
    
    public void addLocation(Location location) {
        this.worldMap.add(location);
    }
    
    public Location at(int... coordinates) {
        CartesianPosition position = CartesianPosition.of(coordinates);
        return at(position);
    }
    
    public Location at(Position position) {
        return findLocation(new PositionLocator(position)).orElse(createLocation(position));
    }
    
    public Optional<Location> findLocation(Predicate<? super Location> matcher) {
        return worldMap.stream().filter(matcher).findFirst();
    }
    
    public Optional<Location> locationOf(Localizable localizable) {
        return findLocation(location -> location.contains(localizable));
    }
    
    // Position
    
    public Position positionOf(Localizable localizable) {
        return locationOf(localizable).map(location -> location.getPosition()).orElse(null);
    }
    
    public Position translate(Position position, Orientation orientation) {
        return translator.translate(position, orientation);
    }
    
    // Utilitaires
    
    private Location createLocation(Position position) {
        Location location = new Location(position);
        worldMap.add(location);
        return location;
    }
    
}
