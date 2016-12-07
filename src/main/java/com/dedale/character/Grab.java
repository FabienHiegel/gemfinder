package com.dedale.character;

import com.dedale.item.Gem;

public class Grab implements PlayerCharacterAction {
    
    private Gem gem;
    
    public Grab(Gem gem) {
        this.gem = gem;
    }
    
    @Override
    public void execute(PlayerCharacter character) {
        character.addGem(gem);
    }
    
}
