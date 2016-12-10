package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;
import com.dedale.character.ability.QuickTurn;

public class TurnClockwise implements PlayerCharacterAction {
	
	private ActionUtils actionUtils;

	public TurnClockwise() {
		actionUtils = new ActionUtils();
	}

	@Override
	public void execute(PlayerCharacter playerCharacter) {
		playerCharacter.setOrientation(playerCharacter.getOrientation().clockwise());

		actionUtils.applyAbility(QuickTurn.class, playerCharacter);
	}

}
