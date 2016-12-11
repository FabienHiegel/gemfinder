package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;
import com.dedale.character.PlayerCharacterAction;
import com.dedale.world.Orientation;

public interface TurnAction extends PlayerCharacterAction {

	Orientation nextOrientation(Orientation playerCharacter);

	default void execute(PlayerCharacter playerCharacter) {
		Orientation orientation = playerCharacter.getOrientation();
		orientation = nextOrientation(orientation);
		playerCharacter.setOrientation(orientation);
	}

}
