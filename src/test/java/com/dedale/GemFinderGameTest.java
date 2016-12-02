package com.dedale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.dedale.character.Move;
import com.dedale.character.PlayerCharacter;
import com.dedale.world.Mountain;
import com.dedale.world.Position;
import com.dedale.world.World;
import com.dedale.world.cartesian.CartesianPosition;
import com.dedale.world.cartesian.CartesianWorld;

public class GemFinderGameTest {
    
    private GemFinderGame game;
    
    @Before
    public void initializeGemFinderGame() {
        game = new GemFinderGame();
    }
    
    @Test
    public void given_a_world_and_a_character_without_moves_charater_final_position_should_be_initial_position() throws Exception {
        // Arrange
        World gameworld = new CartesianWorld();
        
        PlayerCharacter character = new PlayerCharacter();
        character.addAvailableAction(new Move(gameworld));
        game.on(gameworld).at(3, 3).addLocalizable(character);
        
        // Act
        game.play();
        
        // Assert
        Position finalPosition = game.positionOf(character);
        assertThat(finalPosition.toString()).isEqualTo("{x:3, y:3}");
    }
    
    @Test
    public void given_a_world_and_a_character_with_moves_I_want_to_know_final_character_position_after_playing() throws Exception {
        // Arrange
        World gameworld = new CartesianWorld();
        PlayerCharacter character = new PlayerCharacter();
        character.addAvailableAction(new Move(gameworld));
        character.setMoves("MMRMLM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        
        // Act
        game.play();
        
        // Assert
        Position finalPosition = game.positionOf(character);
        assertThat(finalPosition.toString()).isEqualTo("{x:3, y:5}");
        assertThat(game.getTurns()).isEqualTo(6);
    }
    
    @Test
    public void given_a_world_with_a_montain_and_a_character_with_moves_I_want_to_know_final_character_position_when_trying_to_cross_mountain()
            throws Exception {
        // Arrange
        World gameworld = new CartesianWorld();
        Mountain mountain = new Mountain();
        
        PlayerCharacter character = new PlayerCharacter();
        character.addAvailableAction(new Move(gameworld));
        character.setMoves("MRMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 3).addLocalizable(mountain);
        
        // Act
        game.play();
        
        // Assert
        Position finalPosition = game.positionOf(character);
        assertThat(finalPosition.toString()).isEqualTo("{x:2, y:3}");
        assertThat(game.getTurns()).isEqualTo(4);
    }
    
    @Test
    public void given_a_world_with_two_characters_with_moves_I_want_to_know_each_character_position() throws Exception {
        // Arrange
        World gameworld = new CartesianWorld();
        
        PlayerCharacter character = new PlayerCharacter();
        character.addAvailableAction(new Move(gameworld));
        character.setMoves("RMM");
        
        PlayerCharacter secondCharacter = new PlayerCharacter();
        secondCharacter.addAvailableAction(new Move(gameworld));
        secondCharacter.setMoves("RMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 2).addLocalizable(secondCharacter);
        
        // Act
        game.play();
        
        // Assert
        assertThat(game.positionOf(character)).isEqualTo(CartesianPosition.of(4, 2));
        assertThat(game.positionOf(secondCharacter)).isEqualTo(CartesianPosition.of(5, 2));
    }
    
    @Test
    public void given_a_world_with_two_characters_with_moves_I_want_each_to_move_once_per_turn() throws Exception {
        // Arrange
        World gameworld = new CartesianWorld();
        
        PlayerCharacter character = new PlayerCharacter();
        character.addAvailableAction(new Move(gameworld));
        character.setMoves("RMM");
        
        PlayerCharacter secondCharacter = new PlayerCharacter();
        secondCharacter.addAvailableAction(new Move(gameworld));
        secondCharacter.setMoves("RMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 2).addLocalizable(secondCharacter);
        
        // Act
        game.play();
        
        // Assert
        assertThat(game.getTurns()).isEqualTo(3);
    }
    
}
