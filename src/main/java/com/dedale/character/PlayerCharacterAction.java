package com.dedale.character;

@FunctionalInterface
public interface PlayerCharacterAction {

	void execute(PlayerCharacter playerCharacter);

	default PlayerCharacterAction andThen(PlayerCharacterAction then) {
		return playerCharacter -> {
			this.execute(playerCharacter);
			then.execute(playerCharacter);
		};
	}

	PlayerCharacterAction DO_NEXT_ACTION = PlayerCharacter::doNextAction;

}