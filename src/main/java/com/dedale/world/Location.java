package com.dedale.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Location {
    
    private Position position;
    private Collection<Localizable> content = new ArrayList<>();
    
    public Location(Position position) {
        this.position = position;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public Location addLocalizable(Localizable localizable) {
        content.add(localizable);
        return this;
    }
    
    public Location removeLocalizable(Localizable localizable) {
        content.remove(localizable);
        return this;
    }
    
    public Collection<Localizable> getContent() {
        return content;
    }
    
    public boolean contains(Localizable localizable) {
        return content.contains(localizable);
    }
    
    public boolean contains(Predicate<Localizable> predicate) {
        return findListOf(predicate).findAny().isPresent();
    }

    protected Stream<Localizable> findListOf(Predicate<Localizable> predicate) {
        return content.stream().filter(predicate);
    }
    
}
