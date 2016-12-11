package com.dedale.character.action;

import com.dedale.character.PlayerCharacterAbility;
import com.dedale.character.PlayerCharacterAction;

public class TurnAbility implements PlayerCharacterAbility {

	private static final int DEFAULT_DEEP = 1;
	private static final int DEFAULT_THRESHOLD = 1;

	private int deep = DEFAULT_DEEP;
	private int threshold = DEFAULT_THRESHOLD;
	private ActionUtils actionUtils = new ActionUtils();

	public TurnAbility() {
		this(DEFAULT_THRESHOLD);
	}

	public TurnAbility(int threshold) {
		this.threshold = threshold;
	}

	@Override
	public PlayerCharacterAction apply(PlayerCharacterAction action) {
		if (deep < threshold) {
			deep++;
			return action.andThen(PlayerCharacterAction.DO_NEXT_ACTION);
		}
		release();
		return action;
	}

	private void release() {
		deep = DEFAULT_DEEP;
	}

	@Override
	public boolean handle(PlayerCharacterAction action) {
		return actionUtils.isSatisfiedBy(action, TurnAction.class::isInstance);
	}

}
