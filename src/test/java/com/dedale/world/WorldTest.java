package com.dedale.world;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.dedale.TestUtils;
import com.dedale.character.PlayerCharacter;
import com.dedale.item.Gem;
import com.dedale.world.cartesian.CartesianPosition;
import com.dedale.world.cartesian.CartesianWorld;
import com.dedale.world.landforms.Mountain;

public class WorldTest {
    
    private CartesianWorld world;
    private Position origin = CartesianPosition.of(0, 0);
    
    @Before
    public void initializeWorld() {
        world = new CartesianWorld();
    }
    
    @Test
    public void should_locate_something_on_a_world() throws Exception {
        // Arrange
        PlayerCharacter character = new PlayerCharacter();
        
        // Act
        Position position = world.at(2, 2).addLocalizable(character).getPosition();
        
        // Assert
        assertThat(position.toString()).isEqualTo("{x:2, y:2}");
    }
    
    @Test
    public void empty_small_world_board_should_look_empty_but_bounded() throws Exception {
        // Act
        String board = world.regionToBoard(origin, 1, 1);
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("empty_3x3_cartesian_world"));
    }
    
    @Test
    public void small_world_with_charater_should_look_having_a_character() throws Exception {
        // Arrange
        world.at(1, 1).addLocalizable(new PlayerCharacter());
        
        // Act
        String board = world.regionToBoard(origin, 1, 1);
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("3x3_cartesian_world_with_character_at_1x1"));
    }
    
    @Test
    public void small_world_with_mountain_should_look_having_a_mountain() throws Exception {
        // Arrange
        world.at(1, 1).addLocalizable(new Mountain());
        
        // Act
        String board = world.regionToBoard(origin, 1, 1);
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("3x3_cartesian_world_with_mountain_at_1x1"));
    }
    
    @Test
    public void small_world_with_gem_should_look_having_a_gem() throws Exception {
        // Arrange
        world.at(1, 1).addLocalizable(new Gem());
        
        // Act
        String board = world.regionToBoard(origin, 1, 1);
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("3x3_cartesian_world_with_gem_at_1x1"));
    }
    
    @Test
    public void small_world_with_player_should_look_having_a_player_when_not_centered_on_origin() throws Exception {
        // Arrange
        world.at(3, 3).addLocalizable(new PlayerCharacter());
        
        // Act
        String board = world.regionToBoard(CartesianPosition.of(3, 3), 1, 1);
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("3x3_cartesian_world_with_character_at_0x0"));
    }
    
    @Test
    public void empty_world_board_should_look_empty_but_bounded() throws Exception {
        // Act
        String board = world.toBoard();
        
        // Assert
        assertThat(board).isEqualTo(getResourceFile("empty_cartesian_world"));
    }
    
    // Utilitaires
    
    private static String getResourceFile(String filePath) {
        return TestUtils.getResourceFile(WorldTest.class, filePath);
    }
    
}
