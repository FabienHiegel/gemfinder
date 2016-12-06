package com.dedale.character;

import com.dedale.world.Location;
import com.dedale.world.Mountain;
import com.dedale.world.Position;
import com.dedale.world.World;

// TODO : faire en sorte  d'externaliser les stratéiges de déplacement lors d'un prochain refactor.
public class Move implements PlayerCharacterAction {
    private World world;
    
    public Move(World world) {
        this.world = world;
    }
    
    @Override
    public void execute(PlayerCharacter playerCharacter) {
        Location location = world
                .locationOf(playerCharacter)
                .orElseThrow(() -> new IllegalStateException("World does not contain player."));
        
        Position position = location.getPosition();
        Position updatedPosition = world.translate(position, playerCharacter.orientation);
        
        if (world.at(updatedPosition).contains(contentItem -> contentItem instanceof Mountain)) {
            return;
        }
        location.removeLocalizable(playerCharacter);
        world.at(updatedPosition).addLocalizable(playerCharacter);
    }
    
}
