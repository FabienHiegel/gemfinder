package com.dedale;

import java.util.List;
import java.util.stream.Collectors;

import com.dedale.character.PlayerCharacter;
import com.dedale.world.Position;
import com.dedale.world.World;

public class GemFinderGame {
    
    private World world;
    private int turnsCount;
    
    public World on(World world) {
        this.world = world;
        return world;
    }
    
    public void play() {
        List<PlayerCharacter> characterList = world
                .getWorldMap()
                .stream()
                .flatMap(pos -> pos.findListOf(PlayerCharacter.class))
                .collect(Collectors.toList());
        int maxTurnsCount = characterList.stream().mapToInt(charact -> charact.getActionQueue().size()).max().getAsInt();
        while (maxTurnsCount > 0) {
            playTurn();
            maxTurnsCount--;
        }
    }
    
    public void playTurn() {
        List<PlayerCharacter> characterList = world
                .getWorldMap()
                .stream()
                .flatMap(pos -> pos.findListOf(PlayerCharacter.class))
                .collect(Collectors.toList());
        
        for (PlayerCharacter playerCharacter : characterList) {
            playerCharacter.doNextAction();
        }
        turnsCount++;
    }
    
    public Position positionOf(PlayerCharacter character) {
        return world.positionOf(character);
    }
    
    public int getTurns() {
        return turnsCount;
    }

    public String printBoard() {
        StringBuilder boardBuilder = new StringBuilder();
        appendWorld(boardBuilder, world);
        return boardBuilder.toString();
    }

    private void appendWorld(StringBuilder boardBuilder, World world) {
        boardBuilder.append(world.toBoard());
    }
    
}
