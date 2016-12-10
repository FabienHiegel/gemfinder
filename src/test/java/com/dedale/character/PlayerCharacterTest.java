package com.dedale.character;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.util.reflection.Whitebox.getInternalState;

import org.junit.Before;
import org.junit.Test;

import com.dedale.character.action.Dig;
import com.dedale.character.action.Fly;
import com.dedale.character.action.FlyAbility;
import com.dedale.character.action.Grab;
import com.dedale.character.action.Move;
import com.dedale.character.action.PlayerCharacterAction;
import com.dedale.character.action.TurnAbility;
import com.dedale.character.action.TurnClockwise;
import com.dedale.character.action.TurnCounterClockwise;
import com.dedale.item.Gem;
import com.dedale.world.Position;
import com.dedale.world.World;
import com.dedale.world.cartesian.CartesianOrientation;
import com.dedale.world.cartesian.CartesianPosition;
import com.dedale.world.cartesian.CartesianWorld;
import com.dedale.world.landforms.Mountain;

public class PlayerCharacterTest {
    
    private CartesianPosition INITIAL_POSITION;
    private World WORLD;
    
    @Before
    public void initializeWorld() {
        WORLD = new CartesianWorld();
        INITIAL_POSITION = cartesian(1, 1);
    }
    
    @Test
    public void character_should_move_to_north_when_facing_north() throws Exception {
        assertMoveForward(CartesianOrientation.NORTH, cartesian(1, 2));
    }
    
    @Test
    public void character_should_move_to_west_when_facing_west() throws Exception {
        assertMoveForward(CartesianOrientation.WEST, cartesian(0, 1));
    }
    
    @Test
    public void character_should_move_to_SOUTH_when_facing_SOUTH() throws Exception {
        assertMoveForward(CartesianOrientation.SOUTH, cartesian(1, 0));
    }
    
    @Test
    public void character_should_move_to_east_when_facing_east() throws Exception {
        assertMoveForward(CartesianOrientation.EAST, cartesian(2, 1));
    }
    
    @Test
    public void character_should_turn_clockwise_when_facing_north() throws Exception {
        assertTurnClockWise(CartesianOrientation.NORTH, CartesianOrientation.EAST);
    }
    
    @Test
    public void character_should_turn_clockwise_when_facing_east() throws Exception {
        assertTurnClockWise(CartesianOrientation.EAST, CartesianOrientation.SOUTH);
    }
    
    @Test
    public void character_should_turn_clockwise_when_facing_south() throws Exception {
        assertTurnClockWise(CartesianOrientation.SOUTH, CartesianOrientation.WEST);
    }
    
    @Test
    public void character_should_turn_clockwise_when_facing_west() throws Exception {
        assertTurnClockWise(CartesianOrientation.WEST, CartesianOrientation.NORTH);
    }
    
    @Test
    public void character_should_turn_counter_clockwise_when_facing_north() throws Exception {
        assertTurnCounterClockWise(CartesianOrientation.NORTH, CartesianOrientation.WEST);
    }
    
    @Test
    public void character_should_turn_counter_clockwise_when_facing_west() throws Exception {
        assertTurnCounterClockWise(CartesianOrientation.WEST, CartesianOrientation.SOUTH);
    }
    
    @Test
    public void character_should_turn_counter_clockwise_when_facing_south() throws Exception {
        assertTurnCounterClockWise(CartesianOrientation.SOUTH, CartesianOrientation.EAST);
    }
    
    @Test
    public void character_should_turn_counter_clockwise_when_facing_east() throws Exception {
        assertTurnCounterClockWise(CartesianOrientation.EAST, CartesianOrientation.NORTH);
    }
    
    @Test
    public void character_should_not_move_on_position_containing_a_mountain() throws Exception {
        // Arrange
    	PlayerCharacter playerCharacter = createCharacter();
        WORLD.at(1, 2).addLocalizable(new Mountain());
        
        // Act
        playerCharacter.doAction(new Move(WORLD));
        Position finalPosition = WORLD.positionOf(playerCharacter);
        
        // Assert
        assertThat(finalPosition).isEqualTo(INITIAL_POSITION);
    }
    
    @Test
    public void character_should_not_move_when_action_queue_is_empty() throws Exception {
        // Arrange
    	PlayerCharacter playerCharacter = createCharacter();        
        assertThat(playerCharacter.getActionQueue()).isEmpty();
        
        // Act
        playerCharacter.play();
        
        // Assert
        Position finalPosition = WORLD.positionOf(playerCharacter);
        assertThat(finalPosition).isEqualTo(INITIAL_POSITION);
    }
    
    @Test
    public void character_should_grab_gem() throws Exception {
        // Arrange
        PlayerCharacter playerCharacter = new PlayerCharacter();
        Gem gem = new Gem();
        PlayerCharacterAction processingAction = new Grab(gem);
        
        // Act
        playerCharacter.doAction(processingAction);
        
        // Assert
        assertThat(playerCharacter.getGems()).contains(gem);
    }
    
    @Test
    public void character_should_dig_then_grab_gem() throws Exception {
        PlayerCharacter playerCharacter = createCharacter();
        Gem gem = new Gem();
        WORLD.at(INITIAL_POSITION).addLocalizable(gem);
        
        // Act
        playerCharacter.doAction(new Dig(WORLD));
        
        // Assert
        assertThat(playerCharacter.getGems()).contains(gem);
    }
    
    @Test
    public void character_with_quickturn_ability_may_move_immediately_after_turn() throws Exception {
        // Arrange
    	PlayerCharacter playerCharacter = createCharacter();
        playerCharacter.addAbility(new TurnAbility(2));
        
        playerCharacter.addAction(new Move(WORLD));
        
        // Act
        playerCharacter.doAction(new TurnClockwise());
        
        // Assert
        assertThat(WORLD.positionOf(playerCharacter)).isEqualTo(cartesian(2, 1));
        assertThat(getInternalState(playerCharacter, "orientation")).isEqualTo(CartesianOrientation.EAST);
    }
    
    @Test
    public void character_with_quickturn_ability_may_turn_immediately_after_turn_but_cannot_move_anymore() throws Exception {
    	// Arrange
    	PlayerCharacter playerCharacter = createCharacter();
    	playerCharacter.addAbility(new TurnAbility(2));
    	
    	playerCharacter.addAction(new TurnClockwise());
    	playerCharacter.addAction(new TurnClockwise());
    	playerCharacter.addAction(new Move(WORLD));
    	playerCharacter.addAction(new TurnClockwise());
    	playerCharacter.addAction(new TurnClockwise());
    	
    	// Act
    	playerCharacter.play();
    	playerCharacter.play();
    	playerCharacter.play();
    	
    	// Assert
    	assertThat(WORLD.positionOf(playerCharacter)).isEqualTo(cartesian(1, 0));
    	assertThat(getInternalState(playerCharacter, "orientation")).isEqualTo(CartesianOrientation.NORTH);
    }
    
    @Test
    public void character_cannot_fly_when_doesnt_have_the_fly_ability() throws Exception {
    	// Arrange
    	PlayerCharacter playerCharacter = createCharacter();
    	
    	// Act
    	playerCharacter.doAction(new Fly(WORLD));
    	
    	// Assert
    	assertThat(WORLD.positionOf(playerCharacter)).isEqualTo(INITIAL_POSITION);
    }
    
    @Test
    public void character_may_fly_when_is_able_to_fly() throws Exception {
    	// Arrange
    	PlayerCharacter playerCharacter = createCharacter();
    	playerCharacter.addAbility(new FlyAbility());
    	
    	// Act
    	playerCharacter.doAction(new Fly(WORLD));
    	
    	// Assert
    	assertThat(WORLD.positionOf(playerCharacter)).isEqualTo(cartesian(1, 2));
    }
    
    @Test
    public void character_may_quick_fly_when_is_able_to_fly_quickly() throws Exception {
    	// Arrange
    	PlayerCharacter playerCharacter = createCharacter();
    	playerCharacter.addAbility(new FlyAbility().quickly(2));
    	
    	playerCharacter.addAction(new Fly(WORLD));
    	playerCharacter.addAction(new Fly(WORLD));
    	playerCharacter.addAction(new Fly(WORLD));
    	
    	// Act
    	playerCharacter.play();
    	
    	// Assert
    	assertThat(WORLD.positionOf(playerCharacter)).isEqualTo(cartesian(1, 3));
    }
    
    @Test
    public void character_may_quick_fly_when_is_able_to_fly_quickly_but_only_to_fly() throws Exception {
    	// Arrange
    	PlayerCharacter playerCharacter = createCharacter();
    	playerCharacter.addAbility(new FlyAbility().quickly(2));
    	
    	playerCharacter.addAction(new Fly(WORLD));
    	playerCharacter.addAction(new Move(WORLD));
    	playerCharacter.addAction(new Fly(WORLD));
    	
    	// Act
    	playerCharacter.play();
    	
    	// Assert
    	assertThat(WORLD.positionOf(playerCharacter)).isEqualTo(cartesian(1, 2));
    }

    
    // Utilitaires
    
    private CartesianPosition cartesian(int x, int y) {
        return CartesianPosition.of(x, y);
    }
    
    private void assertMoveForward(CartesianOrientation initialOrientation, CartesianPosition expectedFinalPosition) {
        assertCharacterMove(new Move(WORLD), INITIAL_POSITION, initialOrientation, expectedFinalPosition, initialOrientation);
    }
    
    private void assertTurnClockWise(CartesianOrientation initialOrientation, CartesianOrientation expectedFinalOrientation) {
        assertCharacterMove(new TurnClockwise(), INITIAL_POSITION, initialOrientation, INITIAL_POSITION, expectedFinalOrientation);
    }
    
    private void assertTurnCounterClockWise(CartesianOrientation initialOrientation, CartesianOrientation expectedFinalOrientation) {
        assertCharacterMove(new TurnCounterClockwise(), INITIAL_POSITION, initialOrientation, INITIAL_POSITION, expectedFinalOrientation);
    }
    
    private void assertCharacterMove(PlayerCharacterAction move, Position initialPosition, CartesianOrientation initialOrientation,
            CartesianPosition expectedFinalPosition, CartesianOrientation expectedFinalOrientation) {
        // Arrange
        PlayerCharacter playerCharacter = new PlayerCharacter(initialOrientation);
        WORLD.at(initialPosition).addLocalizable(playerCharacter);
        
        // Act
        playerCharacter.doAction(move);
        Position finalPosition = WORLD.positionOf(playerCharacter);
        
        // Assert
        assertThat(finalPosition).isEqualTo(expectedFinalPosition);
        assertThat(getInternalState(playerCharacter, "orientation")).isEqualTo(expectedFinalOrientation);
    }
    
    private PlayerCharacter createCharacter() {
    	PlayerCharacter playerCharacter = new PlayerCharacter();
    	WORLD.at(INITIAL_POSITION).addLocalizable(playerCharacter);
    	return playerCharacter;
    }
}
