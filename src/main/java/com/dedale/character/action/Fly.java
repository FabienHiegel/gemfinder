package com.dedale.character.action;

import java.util.Optional;

import com.dedale.character.PlayerCharacter;
import com.dedale.world.Location;
import com.dedale.world.Position;
import com.dedale.world.World;

public class Fly implements PlayerCharacterAction {

	private World world;

	public Fly(World world) {
		this.world = world;
	}

	@Override
	public void execute(PlayerCharacter playerCharacter) {
		Optional<FlyAbility> flyAbility = playerCharacter.findAbility(FlyAbility.class);
		if (!flyAbility.isPresent()) {
			return;
		}
		
		internalExecute(playerCharacter);
	}

	private void internalExecute(PlayerCharacter playerCharacter) {
		Location location = world
                .locationOf(playerCharacter)
                .orElseThrow(() -> new IllegalStateException("World does not contain player."));
        
        Position position = location.getPosition();
        Position updatedPosition = world.translate(position, playerCharacter.getOrientation());
        
        location.removeLocalizable(playerCharacter);
        world.at(updatedPosition).addLocalizable(playerCharacter);
	}

}
