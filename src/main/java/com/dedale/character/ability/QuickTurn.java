package com.dedale.character.ability;

import com.dedale.character.PlayerCharacter;

public class QuickTurn implements Ability {

	private static final int DEFAULT_THRESHOLD = 1;

	private int deep;
	private int threshold = DEFAULT_THRESHOLD;

	public QuickTurn() {
		this(DEFAULT_THRESHOLD);
	}

	public QuickTurn(int threshold) {
		this.threshold = threshold;
	}

	@Override
	public void apply(PlayerCharacter playerCharacter) {
		if (deep < threshold) {
			deep++;
			playerCharacter.play();
		}
		release();
	}

	private void release() {
		deep = 0;
	}

}
