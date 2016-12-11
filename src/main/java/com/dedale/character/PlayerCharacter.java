package com.dedale.character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.dedale.character.action.TurnClockwise;
import com.dedale.character.action.TurnCounterClockwise;
import com.dedale.item.Gem;
import com.dedale.world.Localizable;
import com.dedale.world.Orientation;
import com.dedale.world.cartesian.CartesianOrientation;

public class PlayerCharacter implements Localizable {

	private Orientation orientation;
	private LinkedList<PlayerCharacterAction> actionQueue = new LinkedList<>();
	// TODO FH : c'est une FACTORY/Pool d'actions
	private Map<String, PlayerCharacterAction> availableActionsByType = new HashMap<>();
	private Collection<Gem> gems = new ArrayList<>();
	private Collection<PlayerCharacterAbility> abilities = new ArrayList<>();

	public PlayerCharacter() {
		this(CartesianOrientation.NORTH);
	}

	PlayerCharacter(Orientation orientation) {
		this.setOrientation(orientation);
		defineAvailableAction("R", new TurnClockwise());
		defineAvailableAction("L", new TurnCounterClockwise());
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	// actions

	public Collection<PlayerCharacterAction> getActionQueue() {
		return actionQueue;
	}

	public void addAction(PlayerCharacterAction action) {
		if (action == null) {
			return;
		}
		actionQueue.add(action);
	}
	
	private void addFirstAction(PlayerCharacterAction action) {
		if (action == null) {
			return;
		}
		actionQueue.addFirst(action);
	}

	public void doNextAction() {
		PlayerCharacterAction processingAction = nextAction();
		doAction(processingAction);
	}
	
	public void doNextActionWhen(Predicate<PlayerCharacterAction> whenAction) {
		PlayerCharacterAction processingAction = nextAction();
		if (whenAction.test(processingAction)) {
			doAction(processingAction);
		} else {
			addFirstAction(processingAction);
		}
	}

	public void doAction(PlayerCharacterAction processingAction) {
		if (processingAction == null) {
			return;
		}
		PlayerCharacterAction action = processingAction;
		Optional<PlayerCharacterAbility> optAbility = findAbility(ability -> ability.handle(processingAction));
		if (optAbility.isPresent()) {
			action = optAbility.get().apply(action);
		}
		
		action.execute(this);
	}

	@Deprecated
	public void setActionQueue(String actionTypes) {
		for (String actionType : actionTypes.split("")) {
			PlayerCharacterAction action = createAction(actionType);
			addAction(action);
		}
	}

	public PlayerCharacterAction nextAction() {
		return actionQueue.pollFirst();
	}

	@Deprecated
	public void defineAvailableAction(String actionType, PlayerCharacterAction action) {
		availableActionsByType.put(actionType, action);
	}

	@Deprecated
	private PlayerCharacterAction createAction(String actionType) {
		return availableActionsByType.get(actionType);
	}

	// inventory

	public Collection<Gem> getGems() {
		return gems;
	}

	public void addGem(Gem gem) {
		gems.add(gem);
	}

	// abilities

	public void addAbility(PlayerCharacterAbility ability) {
		abilities.add(ability);
	}

	public Optional<PlayerCharacterAbility> findAbility(Predicate<PlayerCharacterAbility> predicate) {
		return abilities.stream().filter(predicate).findFirst();
	}

	public <A extends PlayerCharacterAbility> Optional<A> findAbility(Class<A> abilityClass) {
		return findAbility(abilityClass::isInstance).map(abilityClass::cast);
	}

	public Stream<PlayerCharacterAbility> findAbilityList(Predicate<PlayerCharacterAbility> predicate) {
		return abilities.stream().filter(predicate);
	}

	public <A extends PlayerCharacterAbility> Stream<A> findAbilityList(Class<A> abilityClass) {
		return findAbilityList(abilityClass::isInstance).map(abilityClass::cast);
	}

}
