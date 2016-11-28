package gemfinder;

import gemfinder.character.GemCharacter;
import gemfinder.world.Orientation;
import gemfinder.world.Position;
import gemfinder.world.World;

public class GemFinderGame {
    
    private World<?, ?> world;
    
    public void play() {
        // TODO Auto-generated method stub
        
    }
    
    public Position<?, ?> positionOf(GemCharacter character) {
        return world.findPosition(position -> position.contains(character)).orElseGet(null);
    }
    
    public int getTurns() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public void setWorld(World<?, ?> world) {
        this.world = world;
    }
    
    public World<?, ?> on(World<?, ?> world) {
        this.world = world;
        return world;
    }
    
}
