package gemfinder.world;

import java.util.Optional;
import java.util.function.Predicate;

public interface World<P extends Position<P, O>, O extends Orientation> {
    
    void addPosition(P position);
    
    P at(int... coordinates);
    
    Optional<P> findPosition(Predicate<? super P> matcher);
}
