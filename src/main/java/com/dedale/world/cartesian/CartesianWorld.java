package com.dedale.world.cartesian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import com.dedale.character.GemCharacter;
import com.dedale.world.World;
import com.dedale.world.cartesian.CartesianPosition.CartesianPositionLocator;

public class CartesianWorld implements World<CartesianPosition, CartesianOrientation> {
    private Collection<CartesianPosition> worldMap = new ArrayList<>();
    
    @Override
    public Collection<CartesianPosition> getWorldMap() {
        return Collections.unmodifiableCollection(worldMap);
    }
    
    @Override
    public void addPosition(CartesianPosition position) {
        this.worldMap.add(position);
    }
    
    @Override
    public CartesianPosition at(int... coordinates) {
        return findPosition(new CartesianPositionLocator(coordinates)).orElse(createAndAddPosition(coordinates));
    }
    
    @Override
    public Optional<CartesianPosition> findPosition(Predicate<? super CartesianPosition> matcher) {
        return worldMap.stream().filter(matcher).findFirst();
    }
    
    @Override
    public CartesianPosition positionOf(GemCharacter character) {
        return findPosition(position -> position.contains(character)).orElse(null);
    }
    
    private CartesianPosition createAndAddPosition(int... coordinates) {
        return new CartesianPosition(this, coordinates[0], coordinates[1]);
    }
    
}
