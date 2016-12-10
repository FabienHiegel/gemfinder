package com.dedale.character.action;

import java.util.Optional;

import com.dedale.character.PlayerCharacter;
import com.dedale.character.ability.Ability;

class ActionUtils {

	public PlayerCharacterAction applyAbility(Class<? extends Ability> abilityClass, PlayerCharacter playerCharacter,
			PlayerCharacterAction action) {
		Optional<? extends Ability> optAbility = playerCharacter.findAbility(abilityClass);
		if (optAbility.isPresent()) {
			return optAbility.get().apply(action);
		}
		return action;
	}

}
