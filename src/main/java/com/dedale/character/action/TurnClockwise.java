package com.dedale.character.action;

import com.dedale.world.Orientation;

public class TurnClockwise implements TurnAction {
	
	public Orientation nextOrientation(Orientation orientation) {
		return orientation.clockwise();
	}

}
