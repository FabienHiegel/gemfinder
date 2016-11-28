package gemfinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import gemfinder.character.GemCharacter;
import gemfinder.world.Position;
import gemfinder.world.World;
import gemfinder.world.cartesian.CartesianWorld;

public class GemFinderGameTest {
    
    private GemFinderGame game;
    
    @Before
    public void initializeGemFinderGame() {
        game = new GemFinderGame();
    }
    
    @Test
    public void given_a_world_and_a_character_without_moves_charater_final_position_should_be_initial_position() throws Exception {
        // Arrange
        World<?, ?> gameworld = new CartesianWorld();
        
        GemCharacter character = new GemCharacter();
        Position<?, ?> characterPosition = gameworld.at(3, 3);
        characterPosition.addLocalizable(character);
        game.setWorld(gameworld);
        
        // Act
        game.play();
        Position<?, ?> finalPosition = game.positionOf(character);
        
        // Assert
        assertThat(finalPosition.toString()).isEqualTo("{x:3, y:3}");
    }
    
    @Test
    public void given_a_world_and_a_character_with_moves_I_want_to_know_final_caracter_position_after_playing() throws Exception {
        // Arrange
        World<?,?> gameworld = new CartesianWorld();
        GemCharacter character = new GemCharacter();
        character.setMoves("MMRMLM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        
        // Act
        game.play();
        Position<?,?> finalPosition = game.positionOf(character);
        
        // Assert
        assertThat(finalPosition.toString()).isEqualTo("{x:3, y:5}");
        assertThat(game.getTurns()).isEqualTo(6);
    }
    
}
