package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;

public class TurnClockwise implements PlayerCharacterAction {
	
	@Override
	public void execute(PlayerCharacter playerCharacter) {
		playerCharacter.setOrientation(playerCharacter.getOrientation().clockwise());
	}

}
