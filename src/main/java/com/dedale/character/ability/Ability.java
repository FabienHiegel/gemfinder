package com.dedale.character.ability;

import com.dedale.character.action.PlayerCharacterAction;

public interface Ability {

	boolean handle(PlayerCharacterAction action);
	
	PlayerCharacterAction apply(PlayerCharacterAction playerCharacter);

}
