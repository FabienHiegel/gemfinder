package gemfinder.world.cartesian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import gemfinder.character.GemCharacter;
import gemfinder.world.Position;

public class CartesianPosition implements Position<CartesianPosition, CartesianOrientation> {
    
    private int x;
    private int y;
    private Collection<GemCharacter> content = new ArrayList<>();
    
    public CartesianPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public CartesianPosition addLocalizable(GemCharacter character) {
        content.add(character);
        return this;
    }
    
    @Override
    public boolean contains(GemCharacter character) {
        return content.contains(character);
    }
    
    @Override
    public String toString() {
        return "{" + "x:" + x + ", " + "y:" + y + "}";
    }
    
    @Override
    public CartesianPosition translate(CartesianOrientation orientation) {
        switch (orientation) {
            case NORTH:
                return new CartesianPosition(x, y + 1);
            case WEST:
                return new CartesianPosition(x - 1, y);
            case SOUTH:
                return new CartesianPosition(x, y - 1);
            case EAST:
                return new CartesianPosition(x + 1, y);
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
