package com.dedale.character.action;

import java.util.Optional;

import com.dedale.character.PlayerCharacter;
import com.dedale.character.ability.Ability;

class ActionUtils {

	public void applyAbility(Class<? extends Ability> abilityClass, PlayerCharacter playerCharacter) {
		Optional<? extends Ability> optAbility = playerCharacter.findAbility(abilityClass);
		optAbility.ifPresent(ability -> ability.apply(playerCharacter));
	}
	
}
