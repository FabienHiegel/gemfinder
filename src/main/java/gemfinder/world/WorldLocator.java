package gemfinder.world;

public interface WorldLocator {
    
    PositionLocator on(World gameworld);
    
    World getWorld();
    
}
