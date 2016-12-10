package com.dedale.character.action;

import com.dedale.character.PlayerCharacter;

@FunctionalInterface
public interface PlayerCharacterAction{
    
    void execute(PlayerCharacter playerCharacter);
    
}