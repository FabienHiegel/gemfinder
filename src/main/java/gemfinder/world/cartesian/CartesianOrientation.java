package gemfinder.world.cartesian;

import gemfinder.world.Orientation;

public enum CartesianOrientation implements Orientation {
    NORTH, EAST, SOUTH, WEST;
    
    @Override
    public Orientation clockwise() {
        return values()[(ordinal() + 1) % values().length];
    }
    
    @Override
    public Orientation counterClockwise() {
        return values()[(ordinal() - 1 + values().length) % values().length];
    }
    
}
