package com.dedale.world;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import com.dedale.character.GemCharacter;

public interface World<P extends Position<P, O>, O extends Orientation> {
    
    Collection<P> getWorldMap();
    
    void addPosition(P position);
    
    P at(int... coordinates);
    
    Optional<P> findPosition(Predicate<? super P> matcher);

    P positionOf(GemCharacter character);
    
}
