package gemfinder.character;

import gemfinder.world.Orientation;
import gemfinder.world.Position;
import gemfinder.world.cartesian.CartesianOrientation;

public class GemCharacter {
    
    private Orientation orientation;
    
    public GemCharacter() {
        this(CartesianOrientation.NORTH);
    }
    
    public GemCharacter(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public void setMoves(String moves) {
        
    }
    
    public <P extends Position<P, O>, O extends Orientation> P move(String move, P position) {
        switch (move) {
            case "M":
                return position.translate((O) orientation);
            case "R":
                orientation = orientation.clockwise();
                return position;
            case "L":
                orientation = orientation.counterClockwise();
                return position;
            default:
                return position;
        }
    }
    
    
}
