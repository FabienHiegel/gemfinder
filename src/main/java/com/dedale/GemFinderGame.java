package com.dedale;

import java.util.List;
import java.util.stream.Collectors;

import com.dedale.character.GemCharacter;
import com.dedale.world.Orientation;
import com.dedale.world.Position;
import com.dedale.world.World;

public class GemFinderGame {
    
    private World<?, ?> world;
    private int turnsCount;
    
    public <P extends Position<P, O>, O extends Orientation> void play() {
        List<GemCharacter> characterList = world.getWorldMap().stream()
                .flatMap(pos -> pos.getContent().stream())
                .filter(loc -> loc instanceof GemCharacter)
                .map(loc -> (GemCharacter) loc)
                .collect(Collectors.toList());
        for (GemCharacter gemCharacter : characterList) {
            List<String> actions = getTurnActions(gemCharacter);
            for (String move : actions) {
                turnsCount++;
                P position = positionOf(gemCharacter);
                gemCharacter.move(move, position);
            }
        }
    }
    
    private List<String> getTurnActions(GemCharacter gemCharacter) {
        return gemCharacter.getMoves();
    }

    public <P extends Position<P, O>, O extends Orientation> P positionOf(GemCharacter character) {
        return (P) world.positionOf(character);
    }
    
    public int getTurns() {
        return turnsCount;
    }
    
    public void setWorld(World<?, ?> world) {
        this.world = world;
    }
    
    public World<?, ?> on(World<?, ?> world) {
        this.world = world;
        return world;
    }
    
}
