package com.dedale.world.cartesian;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.world.cartesian.CartesianPosition;
import com.dedale.world.cartesian.CartesianWorld;

public class CartesianPositionTest {
    
    @Test
    public void should_be_shown_as_natural_cartesian_coordinates() throws Exception {
        // Arrange
        CartesianPosition position = new CartesianPosition(new CartesianWorld(), 2, 2);
        
        // Act
        String string = position.toString();
        
        // Assert
        assertThat(string).isEqualTo("{x:2, y:2}");
    }
    
}
