package com.dedale.world;

import java.util.Collection;

public interface Position<P extends Position<P, O>, O extends Orientation> {
    
    Collection<Localizable> getContent();
    
    P addLocalizable(Localizable character);
    
    P removeLocalizable(Localizable character);
    
    boolean contains(Localizable character);
    
    P translate(O orientation);

}
