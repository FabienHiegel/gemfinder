package com.dedale.character;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.dedale.world.Localizable;
import com.dedale.world.Mountain;
import com.dedale.world.Orientation;
import com.dedale.world.Position;
import com.dedale.world.cartesian.CartesianOrientation;

public class GemCharacter implements Localizable {
    
    private Orientation orientation;
    private List<String> moves = Collections.emptyList();
    
    public GemCharacter() {
        this(CartesianOrientation.NORTH);
    }
    
    GemCharacter(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public <P extends Position<P, O>, O extends Orientation> void move(String move, P position) {
        switch (move) {
            case "M":
                // TODO : faire en sorte  d'externaliser les stratéiges de déplacement lors d'un prochain refactor.
                position.removeLocalizable(this);
                P updatedPosition = position.translate((O) orientation);
                if (updatedPosition.getContent()
                        .stream()
                        .filter(contentItem -> contentItem instanceof Mountain)
                        .findFirst()
                        .isPresent()) {
                    updatedPosition = position;
                }
                 updatedPosition.addLocalizable(this);
                 break;
            case "R":
                orientation = orientation.clockwise();
                break;
            case "L":
                orientation = orientation.counterClockwise();
                break;
            default:
                // DO NOTHING
        }
    }
    
    public List<String> getMoves() {
        return moves;
    }
    
    public void setMoves(String moves) {
        this.moves = Arrays.asList(moves.split(""));
    }
    
}
