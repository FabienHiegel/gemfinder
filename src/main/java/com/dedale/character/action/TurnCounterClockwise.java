package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;

public class TurnCounterClockwise implements PlayerCharacterAction {

	@Override
	public void execute(PlayerCharacter playerCharacter) {
		playerCharacter.setOrientation(playerCharacter.getOrientation().counterClockwise());
	}

}
