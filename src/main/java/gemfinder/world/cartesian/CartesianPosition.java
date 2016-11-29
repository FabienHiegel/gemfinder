package gemfinder.world.cartesian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import gemfinder.world.Localizable;
import gemfinder.world.Position;

public class CartesianPosition implements Position<CartesianPosition, CartesianOrientation> {
    
    private int x;
    private int y;
    private Collection<Localizable> content = new ArrayList<>();
    private CartesianWorld cartesianWorld;
    
    public CartesianPosition(CartesianWorld cartesianWorld, int x, int y) {
        this.cartesianWorld = cartesianWorld;
        cartesianWorld.addPosition(this);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public CartesianPosition addLocalizable(Localizable localizable) {
        content.add(localizable);
        return this;
    }
    
    @Override
    public CartesianPosition removeLocalizable(Localizable localizable) {
        content.remove(localizable);
        return this;
    }
    
    @Override
    public Collection<Localizable> getContent() {
        return content;
    }
    
    @Override
    public boolean contains(Localizable localizable) {
        return content.contains(localizable);
    }
    
    @Override
    public String toString() {
        return "{" + "x:" + x + ", " + "y:" + y + "}";
    }
    
    @Override
    public CartesianPosition translate(CartesianOrientation orientation) {
        switch (orientation) {
            case NORTH:
                return new CartesianPosition(cartesianWorld, x, y + 1);
            case WEST:
                return new CartesianPosition(cartesianWorld, x - 1, y);
            case SOUTH:
                return new CartesianPosition(cartesianWorld, x, y - 1);
            case EAST:
                return new CartesianPosition(cartesianWorld, x + 1, y);
            default:
                return this;
        }
    }
    
    static class CartesianPositionLocator implements Predicate<CartesianPosition> {
        
        private int y;
        private int x;
        
        public CartesianPositionLocator(int... coordinates) {
            this.x = coordinates[0];
            this.y = coordinates[1];
        }
        
        @Override
        public boolean test(CartesianPosition position) {
            return this.x == position.x && this.y == position.y;
        }
        
    }
    
}
