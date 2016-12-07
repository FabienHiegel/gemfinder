package com.dedale.world.cartesian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import com.dedale.character.PlayerCharacter;
import com.dedale.item.Gem;
import com.dedale.world.Localizable;
import com.dedale.world.Location;
import com.dedale.world.Orientation;
import com.dedale.world.Position;
import com.dedale.world.World;
import com.dedale.world.landforms.Mountain;

public class CartesianWorld implements World {
    private Collection<Location> worldMap = new ArrayList<>();
    private CartesianTranslator translator = new CartesianTranslator();
    
    public Collection<Location> getWorldMap() {
        return Collections.unmodifiableCollection(worldMap);
    }
    
    // Location
    
    public void addLocation(Location location) {
        worldMap.add(location);
    }
    
    public Location at(int... coordinates) {
        return at(CartesianPosition.of(coordinates));
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
        addLocation(location);
        return location;
    }
    
    @Override
    public String toBoard() {
        return regionToBoard(CartesianPosition.of(0, 0), 10, 10);
    }
    
    @Override
    public String regionToBoard(Position origin, int... coordinatesBounds) {
        CartesianPosition cartesianOrigin = (CartesianPosition) origin;
        CartesianPosition maxTopRightPosition = CartesianPosition.of(coordinatesBounds);
        
        int oX = cartesianOrigin.x;
        int oY = cartesianOrigin.y;
        int maxX = maxTopRightPosition.x;
        int maxY = maxTopRightPosition.y;
        
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("|");
        for (int x = -maxX; x <= maxX; x++) {
            resultBuilder.append("--");
        }
        resultBuilder.append("|\r\n");
        for (int y = oY + maxY; y >= oY - maxY; y--) {
            resultBuilder.append("|");
            for (int x = oX - maxX; x <= oX + maxX; x++) {
                Optional<Location> location = findLocation(new PositionLocator(CartesianPosition.of(x, y)));
                if (location.isPresent()) {
                    renderLocation(resultBuilder, location.get());
                } else {
                    resultBuilder.append("  ");
                }
            }
            resultBuilder.append("|\r\n");
        }
        resultBuilder.append("|");
        for (int x = -maxX; x <= maxX; x++) {
            resultBuilder.append("--");
        }
        resultBuilder.append("|");
        return resultBuilder.toString();
    }
    
    private void renderLocation(StringBuilder resultBuilder, Location location) {
        if (location.findOneOf(PlayerCharacter.class::isInstance).isPresent()) {
            resultBuilder.append(" C");
            return;
        }
        if (location.findOneOf(Mountain.class::isInstance).isPresent()) {
            resultBuilder.append(" M");
            return;
        }
        if (location.findOneOf(Gem.class::isInstance).isPresent()) {
            resultBuilder.append("<>");
            return;
        }
    }
    
}
