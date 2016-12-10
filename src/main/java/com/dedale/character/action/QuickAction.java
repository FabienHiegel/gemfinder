package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;

public class QuickAction implements PlayerCharacterAction {

	private PlayerCharacterAction action;

	public QuickAction(PlayerCharacterAction action) {
		this.action = action;
	}

	@Override
	public void execute(PlayerCharacter playerCharacter) {
		action.execute(playerCharacter);
		playerCharacter.play();
	}

}
