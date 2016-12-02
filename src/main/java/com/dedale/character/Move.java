package com.dedale.character;

import java.util.Optional;

import com.dedale.world.Location;
import com.dedale.world.Mountain;
import com.dedale.world.Position;
import com.dedale.world.World;

// TODO : faire en sorte  d'externaliser les stratéiges de déplacement lors d'un prochain refactor.
public class Move implements PlayerCharacterAction<PlayerCharacter> {
    private World world;
    
    public Move(World world) {
        this.world = world;
    }
    
    @Override
    public void call(PlayerCharacter playerCharacter) {
        Optional<Location> optLocation = world.locationOf(playerCharacter);
        if (!optLocation.isPresent()) {
            return;
        }
        
        Location location = optLocation.get();
        Position position = location.getPosition();
        Position updatedPosition = world.translate(position, playerCharacter.orientation);
        
        if (world.at(updatedPosition).contains(contentItem -> contentItem instanceof Mountain)) {
            return;
        }
        location.removeLocalizable(playerCharacter);
        world.at(updatedPosition).addLocalizable(playerCharacter);
    }
    
}
