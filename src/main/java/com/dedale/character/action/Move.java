package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;
import com.dedale.character.PlayerCharacterAction;
import com.dedale.world.Location;
import com.dedale.world.Position;
import com.dedale.world.World;
import com.dedale.world.landforms.Mountain;

// TODO : faire en sorte  d'externaliser les stratégies de déplacement lors d'un prochain refactor.
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
        Position updatedPosition = world.translate(position, playerCharacter.getOrientation());
        
        if (world.at(updatedPosition).contains(contentItem -> contentItem instanceof Mountain)) {
            return;
        }
        location.removeLocalizable(playerCharacter);
        world.at(updatedPosition).addLocalizable(playerCharacter);
    }
    
}
