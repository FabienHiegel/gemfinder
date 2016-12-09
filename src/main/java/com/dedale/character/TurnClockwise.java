package com.dedale.character;

public class TurnClockwise implements PlayerCharacterAction {
    
    @Override
    public void execute(PlayerCharacter playerCharacter) {
        playerCharacter.orientation = playerCharacter.orientation.clockwise();
    }
    
}
