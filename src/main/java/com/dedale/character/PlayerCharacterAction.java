package com.dedale.character;

@FunctionalInterface
interface PlayerCharacterAction<T extends PlayerCharacter>{
    
    void call(T character);
    
}