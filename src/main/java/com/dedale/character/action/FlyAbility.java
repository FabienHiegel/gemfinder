package com.dedale.character.action;

import com.dedale.character.PlayerCharacterAbility;
import com.dedale.character.PlayerCharacterAction;

public class FlyAbility implements PlayerCharacterAbility {

	private static final int DEFAULT_DEEP = 1;
	private static final int DEFAULT_THRESHOLD = 1;

	private int deep = DEFAULT_DEEP;
	private int threshold = DEFAULT_THRESHOLD;

	private ActionUtils actionUtils = new ActionUtils();

	public PlayerCharacterAction apply(PlayerCharacterAction action) {
		if (deep < threshold) {
			deep++;
			return action.andThen(playerCharacter -> playerCharacter.doNextActionWhen(FlyAction.class::isInstance));
		}
		release();
		return action;
	}

	private void release() {
		deep = DEFAULT_DEEP;
	}

	public FlyAbility quickly(int successiveFlies) {
		this.threshold = successiveFlies;
		return this;
	}

	@Override
	public boolean handle(PlayerCharacterAction action) {
		return actionUtils.isSatisfiedBy(action, FlyAction.class::isInstance);
	}

}
