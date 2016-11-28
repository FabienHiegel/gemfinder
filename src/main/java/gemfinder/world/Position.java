package gemfinder.world;

import gemfinder.character.GemCharacter;

public interface Position<P extends Position<P, O>, O extends Orientation> {
    
    P addLocalizable(GemCharacter character);
    
    boolean contains(GemCharacter character);
    
    P translate(O orientation);
}
