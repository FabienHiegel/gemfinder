package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;
import com.dedale.character.ability.QuickTurn;

public class TurnCounterClockwise implements PlayerCharacterAction {

	private ActionUtils actionUtils;

	public TurnCounterClockwise() {
		actionUtils = new ActionUtils();
	}

	@Override
	public void execute(PlayerCharacter playerCharacter) {
		playerCharacter.setOrientation(playerCharacter.getOrientation().counterClockwise());

		actionUtils.applyAbility(QuickTurn.class, playerCharacter);
	}

}
