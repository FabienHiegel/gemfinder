package gemfinder;

import static org.assertj.core.api.Assertions.assertThat;

import javax.swing.text.Position;

import org.junit.Before;
import org.junit.Test;

import gemfinder.character.GemCharacter;
import gemfinder.world.CartesianWorld;
import gemfinder.world.World;

public class GemFinderGameTest {
    
    private GemFinderGame game;
    
    @Before
    public void initializeGemFinderGame() {
        game = new GemFinderGame();
    }
    
    @Test
    public void given_a_world_and_a_character_with_moves_I_want_to_know_final_caracter_position_after_playing() throws Exception {
        // Arrange
        World gameworld = new CartesianWorld();
        GemCharacter character = new GemCharacter();
        character.setMoves("MMRMLM");
        
        game.put(character).on(gameworld).at(2, 2);
        
        // Act
        game.play();
        Position finalPosition = game.positionOf(character);
        
        // Assert
        assertThat(finalPosition.toString()).isEqualTo("{x:3, y:5}");
        assertThat(game.getTurns()).isEqualTo(6);
    }
    
}
