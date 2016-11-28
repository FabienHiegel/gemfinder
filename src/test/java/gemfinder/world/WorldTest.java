package gemfinder.world;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import gemfinder.character.GemCharacter;
import gemfinder.world.cartesian.CartesianOrientation;
import gemfinder.world.cartesian.CartesianPosition;
import gemfinder.world.cartesian.CartesianWorld;

public class WorldTest {
    
    @Test
    public void should_locate_something_on_a_world() throws Exception {
        // Arrange
        World<CartesianPosition, CartesianOrientation> world = new CartesianWorld();
        GemCharacter character = new GemCharacter();
        
        // Act
        Position<CartesianPosition, CartesianOrientation> position = world.at(2, 2).addLocalizable(character);
        
        // Assert
        assertThat(position.toString()).isEqualTo("{x:2, y:2}");
    }
    
}
