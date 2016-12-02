package com.dedale.world.cartesian;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CartesianPositionTest {
    
    @Test
    public void should_be_shown_as_natural_cartesian_coordinates() throws Exception {
        // Arrange
        CartesianPosition position = new CartesianPosition(2, 2);
        
        // Act
        String string = position.toString();
        
        // Assert
        assertThat(string).isEqualTo("{x:2, y:2}");
    }
    
    @Test
    public void should_two_positions_for_same_point_are_not_same_instance_but_are_equals() throws Exception {
        CartesianPosition position = new CartesianPosition(2, 2);
        CartesianPosition positions = new CartesianPosition(2, 2);
        
        assertThat(position).isNotSameAs(positions);
        assertThat(position).isEqualTo(positions);
    }
    
    @Test
    public void should_positions_for_same_point_from_ofmethod_are_same_instance_and_equals() throws Exception {
        CartesianPosition position = CartesianPosition.of(2, 2);
        CartesianPosition positions = CartesianPosition.of(2, 2);
        
        assertThat(position).isSameAs(positions);
        assertThat(position).isEqualTo(positions);
    }
    
}
