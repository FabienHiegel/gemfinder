package com.dedale.character.action;

import com.dedale.character.ability.Ability;

public class TurnAbility implements Ability {

	private static final int DEFAULT_DEEP = 1;
	private static final int DEFAULT_THRESHOLD = 1;

	private int deep = DEFAULT_DEEP;
	private int threshold = DEFAULT_THRESHOLD;

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
			return new QuickAction(action);
		}
		release();
		return action;
	}

	private void release() {
		deep = DEFAULT_DEEP;
	}

	@Override
	public boolean handle(PlayerCharacterAction action) {
		return action instanceof TurnClockwise || action instanceof TurnCounterClockwise;
	}

}
