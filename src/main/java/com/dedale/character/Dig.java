package com.dedale.character;

import com.dedale.item.Gem;
import com.dedale.world.Location;
import com.dedale.world.World;

public class Dig implements PlayerCharacterAction {
    
    private World world;
    
    public Dig(World world) {
        this.world = world;
    }
    
    @Override
    public void execute(PlayerCharacter playerCharacter) {
        Location location = world
                .locationOf(playerCharacter)
                .orElseThrow(() -> new IllegalStateException("World does not contain player."));
        
        location.findListOf(Gem.class).findFirst().ifPresent(gem -> playerGrabGem(playerCharacter, gem));
    }
    
    private void playerGrabGem(PlayerCharacter playerCharacter, Gem gem) {
        playerCharacter.doAction(new Grab(gem));
    }
    
}
