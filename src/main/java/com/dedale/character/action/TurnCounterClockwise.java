package com.dedale.character.action;

import com.dedale.world.Orientation;

public class TurnCounterClockwise implements TurnAction {

	public Orientation nextOrientation(Orientation orientation) {
		return orientation.counterClockwise();
	}

}
