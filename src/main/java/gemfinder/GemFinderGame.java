package gemfinder;

import java.util.List;
import java.util.stream.Collectors;

import gemfinder.character.GemCharacter;
import gemfinder.world.Orientation;
import gemfinder.world.Position;
import gemfinder.world.World;

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
            P position = positionOf(gemCharacter);
            List<String> actionss = getTurnActions(gemCharacter);
            for (String move : actionss) {
                turnsCount++;
                position = gemCharacter.move(move, position);
            }
        }
    }
    
    private List<String> getTurnActions(GemCharacter gemCharacter) {
        return gemCharacter.getMoves();
    }

    public <P extends Position<P, O>, O extends Orientation> P positionOf(GemCharacter character) {
        return (P) world.findPosition(position -> position.contains(character)).orElse(null);
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
