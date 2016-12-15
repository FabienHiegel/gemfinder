package com.dedale;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import com.dedale.character.Dig;
import com.dedale.character.Move;
import com.dedale.character.PlayerCharacter;
import com.dedale.item.Gem;
import com.dedale.world.World;
import com.dedale.world.cartesian.CartesianPosition;
import com.dedale.world.cartesian.CartesianWorld;
import com.dedale.world.landforms.Mountain;

public class GemFinderGameTest {
    
    private GemFinderGame game;
    private World gameworld;
    
    @Before
    public void initializeGemFinderGame() {
        game = new GemFinderGame();
    }
    
    @Before
    public void initializeWorld() {
        gameworld = new CartesianWorld();
    }
    
    @Test
    public void given_a_world_and_a_character_without_moves_charater_final_position_should_be_initial_position() throws Exception {
        // Arrange
        PlayerCharacter character = new PlayerCharacter();
        game.on(gameworld).at(3, 3).addLocalizable(character);
        
        // Act
        game.play();
        
        // Assert
        assertThat(game.positionOf(character)).isEqualTo(CartesianPosition.of(3, 3));
    }
    
    @Test
    public void given_a_world_and_a_character_with_moves_I_want_to_know_final_character_position_after_playing() throws Exception {
        PlayerCharacter character = createCharacter();
        character.setActionQueue("MMRMLM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        
        // Act
        game.play();
        
        // Assert
        assertThat(game.positionOf(character)).isEqualTo(CartesianPosition.of(3, 5));
        assertThat(game.getTurns()).isEqualTo(6);
    }
    
    @Test
    public void given_a_world_with_a_montain_and_a_character_with_moves_I_want_to_know_final_character_position_when_trying_to_cross_mountain()
            throws Exception {
        // Arrange
        Mountain mountain = new Mountain();
        
        PlayerCharacter character = createCharacter();
        character.setActionQueue("MRMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 3).addLocalizable(mountain);
        
        // Act
        game.play();
        
        // Assert
        assertThat(game.positionOf(character)).isEqualTo(CartesianPosition.of(2, 3));
        assertThat(game.getTurns()).isEqualTo(4);
    }
    
    @Test
    public void given_a_world_with_two_characters_with_moves_I_want_to_know_each_character_position() throws Exception {
        PlayerCharacter character = createCharacter();
        character.setActionQueue("RMM");
        
        PlayerCharacter secondCharacter = createCharacter();
        secondCharacter.setActionQueue("RMM");
        
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
        PlayerCharacter character = createCharacter();
        character.setActionQueue("RMM");
        
        PlayerCharacter secondCharacter = createCharacter();
        secondCharacter.setActionQueue("RMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 2).addLocalizable(secondCharacter);
        
        // Act
        game.play();
        
        // Assert
        assertThat(game.getTurns()).isEqualTo(3);
    }
    
    @Test
    public void given_a_world_with_two_characters_with_moves_I_want_them_to_move_only_once_for_a_turn() throws Exception {
        PlayerCharacter character = createCharacter();
        character.setActionQueue("MMM");
        
        PlayerCharacter secondCharacter = createCharacter();
        secondCharacter.setActionQueue("MMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 2).addLocalizable(secondCharacter);
        
        // Act
        game.playTurn();
        
        // Assert
        assertThat(game.getTurns()).isEqualTo(1);
        assertThat(game.positionOf(character)).isEqualTo(CartesianPosition.of(2, 3));
        assertThat(game.positionOf(secondCharacter)).isEqualTo(CartesianPosition.of(3, 3));
    }
    
    @Test
    public void given_a_world_with_two_characters_with_moves_I_want_them_to_move_twice_for_two_turns() throws Exception {
        PlayerCharacter character = createCharacter();
        character.setActionQueue("MRM");
        
        PlayerCharacter secondCharacter = createCharacter();
        secondCharacter.setActionQueue("MMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 2).addLocalizable(secondCharacter);
        
        // Act
        game.playTurn();
        game.playTurn();
        
        // Assert
        assertThat(game.getTurns()).isEqualTo(2);
        assertThat(game.positionOf(character)).isEqualTo(CartesianPosition.of(2, 3));
        assertThat(game.positionOf(secondCharacter)).isEqualTo(CartesianPosition.of(3, 4));
    }
    
    @Test
    public void given_a_world_with_one_character_I_want_it_to_be_poor_when_didnt_find_gem() throws Exception {
        // Arrange
        PlayerCharacter character = createCharacter();
        character.setActionQueue("MMM");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(3, 2).addLocalizable(new Gem());
        
        // Act
        game.play();
        
        // Assert
        assertThat(character.getGems()).isEmpty();
    }
    
    @Test
    public void given_a_world_with_one_character_I_want_it_to_be_rich_when_found_gem() throws Exception {
        // Arrange
        PlayerCharacter character = createCharacter();
        character.setActionQueue("MD");
        
        game.on(gameworld).at(2, 2).addLocalizable(character);
        game.on(gameworld).at(2, 3).addLocalizable(new Gem());
        
        // Act
        game.play();
        
        // Assert
        assertThat(character.getGems()).hasSize(1);
    }
    
    @Test
    public void given_a_world_with_differents_item_I_want_to_print_board_before_everything_changed() throws Exception {
        // Arrange
        game.on(gameworld).at(2, 2).addLocalizable(createCharacter());
        game.on(gameworld).at(2, 3).addLocalizable(new Gem());
        doNTimes(5, i -> game.on(gameworld).at(4 + i, 4).addLocalizable(new Mountain()));
        
        // Act
        String board = game.printBoard();
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("board"));
    }
    
    // Utilitaires
    
    private <R> R doNTimes(int n, Function<Integer, R> function) {
        R result = (R) Void.TYPE;
        for (int i = 1; i <= n; i++) {
            result = function.apply(i);
        }
        return result;
    }
    
    private PlayerCharacter createCharacter() {
        PlayerCharacter character = new PlayerCharacter();
        character.defineAvailableAction("M", new Move(gameworld));
        character.defineAvailableAction("D", new Dig(gameworld));
        return character;
    }
    
    private static String getResourceFile(String filePath) {
        return TestUtils.getResourceFile(GemFinderGameTest.class, filePath);
    }
    
}
