package gemfinder.world.cartesian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import gemfinder.world.World;
import gemfinder.world.cartesian.CartesianPosition.CartesianPositionLocator;

public class CartesianWorld implements World<CartesianPosition, CartesianOrientation> {
    private Collection<CartesianPosition> worldMap = new ArrayList<>();
    
    @Override
    public void addPosition(CartesianPosition position) {
        this.worldMap.add(position);
    }
    
    @Override
    public CartesianPosition at(int... coordinates) {
        return findPosition(new CartesianPositionLocator(coordinates)).orElse(createPosition(coordinates));
    }
    
    @Override
    public Optional<CartesianPosition> findPosition(Predicate<? super CartesianPosition> matcher) {
        return worldMap.stream().filter(matcher).findFirst();
    }
    
    private CartesianPosition createPosition(int... coordinates) {
        CartesianPosition position = new CartesianPosition(coordinates[0], coordinates[1]);
        addPosition(position);
        return position;
    }
    
}
