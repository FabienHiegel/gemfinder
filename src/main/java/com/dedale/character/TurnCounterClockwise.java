package com.dedale.character;

public class TurnCounterClockwise implements PlayerCharacterAction {
    
    @Override
    public void execute(PlayerCharacter playerCharacter) {
        playerCharacter.orientation = playerCharacter.orientation.counterClockwise();
            }
    
}
