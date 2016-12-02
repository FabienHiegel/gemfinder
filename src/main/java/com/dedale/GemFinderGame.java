package com.dedale;

import java.util.List;
import java.util.stream.Collectors;

import com.dedale.character.PlayerCharacter;
import com.dedale.world.Position;
import com.dedale.world.World;

public class GemFinderGame {
    
    private World world;
    private int turnsCount;
    
    public void play() {
        List<PlayerCharacter> characterList = world
                .getWorldMap()
                .stream()
                .flatMap(pos -> pos.getContent().stream())
                .filter(loc -> loc instanceof PlayerCharacter)
                .map(loc -> (PlayerCharacter) loc)
                .collect(Collectors.toList());
        turnsCount = characterList.stream().mapToInt(charact -> charact.getMoves().size()).max().getAsInt();
        for (int turn = 0; turn < turnsCount; turn++) {
            for (PlayerCharacter gemCharacter : characterList) {
                List<String> turnActions = getTurnActions(gemCharacter);
                if (turnActions.size() < turn) {
                    continue;
                }
                
                String action = turnActions.get(turn);
                gemCharacter.move(action);
            }
        }
    }
    
    private List<String> getTurnActions(PlayerCharacter gemCharacter) {
        return gemCharacter.getMoves();
    }
    
    public Position positionOf(PlayerCharacter character) {
        return world.positionOf(character);
    }
    
    public int getTurns() {
        return turnsCount;
    }
    
    public void setWorld(World world) {
        this.world = world;
    }
    
    public World on(World world) {
        this.world = world;
        return world;
    }
    
}
