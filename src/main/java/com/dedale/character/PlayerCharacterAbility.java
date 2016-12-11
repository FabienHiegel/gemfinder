package com.dedale.character;

public interface PlayerCharacterAbility {

	boolean handle(PlayerCharacterAction action);
	
	PlayerCharacterAction apply(PlayerCharacterAction playerCharacter);

}
