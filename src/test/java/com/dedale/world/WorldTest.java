package com.dedale.world;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.character.PlayerCharacter;
import com.dedale.world.cartesian.CartesianWorld;

public class WorldTest {
    
    @Test
    public void should_locate_something_on_a_world() throws Exception {
        // Arrange
        World world = new CartesianWorld();
        PlayerCharacter character = new PlayerCharacter();
        
        // Act
        Position position = world.at(2, 2).addLocalizable(character).getPosition();
        
        // Assert
        assertThat(position.toString()).isEqualTo("{x:2, y:2}");
    }
    
}
