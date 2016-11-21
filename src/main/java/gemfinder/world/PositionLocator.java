package gemfinder.world;

public interface PositionLocator {
    
    void at(int... coordinates);
    
    Position getPosition();
}
