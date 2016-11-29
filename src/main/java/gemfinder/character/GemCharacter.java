package gemfinder.character;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gemfinder.world.Localizable;
import gemfinder.world.Orientation;
import gemfinder.world.Position;
import gemfinder.world.cartesian.CartesianOrientation;

public class GemCharacter implements Localizable{
    
    private Orientation orientation;
    private List<String> moves = Collections.emptyList();
    
    public GemCharacter() {
        this(CartesianOrientation.NORTH);
    }
    
    GemCharacter(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public <P extends Position<P, O>, O extends Orientation> P move(String move, P position) {
        position.removeLocalizable(this);
        switch (move) {
            case "M":
                return position.translate((O) orientation).addLocalizable(this);
            case "R":
                orientation = orientation.clockwise();
                return position.addLocalizable(this);
            case "L":
                orientation = orientation.counterClockwise();
                return position;
            default:
                return position.addLocalizable(this);
        }
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = Arrays.asList(moves.split(""));
    }
    
    
}
