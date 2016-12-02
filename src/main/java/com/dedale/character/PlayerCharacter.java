package com.dedale.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.dedale.world.Localizable;
import com.dedale.world.Orientation;
import com.dedale.world.cartesian.CartesianOrientation;

public class PlayerCharacter implements Localizable {
    
    Orientation orientation;
    private List<String> moves = Collections.emptyList();
    private List<PlayerCharacterAction<? super PlayerCharacter>> availableActions = new ArrayList<>();
    
    public PlayerCharacter() {
        this(CartesianOrientation.NORTH);
    }
    
    PlayerCharacter(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public void addAvailableAction(PlayerCharacterAction<? super PlayerCharacter> move) {
        availableActions.add(move);
    }
    
    public void move(String move) {
        switch (move) {
            case "M":
                for (PlayerCharacterAction<? super PlayerCharacter> playerCharacterAction : availableActions) {
                    playerCharacterAction.call(this);
                }
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
