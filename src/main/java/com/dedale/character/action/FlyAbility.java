package com.dedale.character.action;

import com.dedale.character.ability.Ability;

public class FlyAbility implements Ability {

	private static final int DEFAULT_DEEP = 1;
	private static final int DEFAULT_THRESHOLD = 1;

	private int deep = DEFAULT_DEEP;
	private int threshold = DEFAULT_THRESHOLD;

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

	public FlyAbility quickly(int successiveFlies) {
		this.threshold = successiveFlies;
		return this;
	}

	@Override
	public boolean handle(PlayerCharacterAction action) {
		return action instanceof Fly;
	}

}
