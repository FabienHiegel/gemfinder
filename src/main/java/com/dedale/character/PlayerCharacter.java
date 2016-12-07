package com.dedale.character;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.dedale.world.Localizable;
import com.dedale.world.Orientation;
import com.dedale.world.cartesian.CartesianOrientation;

public class PlayerCharacter implements Localizable {
    
    Orientation orientation;
    private LinkedList<PlayerCharacterAction> actionQueue = new LinkedList<>();
    // TODO FH : c'est une FACTORY/Pool d'actions
    private Map<String, PlayerCharacterAction> availableActionsByType = new HashMap<>();
    
    public PlayerCharacter() {
        this(CartesianOrientation.NORTH);
    }
    
    PlayerCharacter(Orientation orientation) {
        this.orientation = orientation;
        defineAvailableAction("R", PlayerCharacter::turnClockwise);
        defineAvailableAction("L", PlayerCharacter::turnCounterClockwise);
    }
    
    public void play() {
        PlayerCharacterAction processingAction = nextAction();
        if (processingAction == null) {
            return;
        }
        doAction(processingAction);
        releaseAction(processingAction);
    }
    
    public void doAction(PlayerCharacterAction processingAction) {
        processingAction.execute(this);
    }
    
    public Collection<PlayerCharacterAction> getActionQueue() {
        return actionQueue;
    }
    
    @Deprecated
    public void setActionQueue(String actionTypes) {
        for (String actionType : actionTypes.split("")) {
            PlayerCharacterAction action = createAction(actionType);
            addAction(action);
        }
    }
    
    public void addAction(PlayerCharacterAction action) {
        actionQueue.add(action);
    }
    
    private PlayerCharacterAction nextAction() {
        return actionQueue.peekFirst();
    }
    
    private void releaseAction(PlayerCharacterAction action) {
        actionQueue.remove(action);
    }
    
    @Deprecated
    public void defineAvailableAction(String actionType, PlayerCharacterAction action) {
        availableActionsByType.put(actionType, action);
    }
    
    @Deprecated
    private PlayerCharacterAction createAction(String actionType) {
        return availableActionsByType.get(actionType);
    }
    
    void turnCounterClockwise() {
        orientation = orientation.counterClockwise();
    }
    
    void turnClockwise() {
        orientation = orientation.clockwise();
    }
    
}
