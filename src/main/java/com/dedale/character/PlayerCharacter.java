package com.dedale.character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.dedale.character.ability.Ability;
import com.dedale.character.ability.QuickTurn;
import com.dedale.character.action.PlayerCharacterAction;
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
	private Collection<Ability> abilities = new ArrayList<>();

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
		actionQueue.add(action);
	}

	public void play() {
		PlayerCharacterAction processingAction = nextAction();
		if (processingAction == null) {
			return;
		}
		doAction(processingAction);
	}

	public void doAction(PlayerCharacterAction processingAction) {
		processingAction.execute(this);
	}

	@Deprecated
	public void setActionQueue(String actionTypes) {
		for (String actionType : actionTypes.split("")) {
			PlayerCharacterAction action = createAction(actionType);
			addAction(action);
		}
	}

	private PlayerCharacterAction nextAction() {
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

	public void addAbility(QuickTurn ability) {
		abilities.add(ability);
	}

	public Optional<Ability> findAbility(Predicate<Ability> predicate) {
		return abilities.stream().filter(predicate).findFirst();
	}

	public <A extends Ability> Optional<A> findAbility(Class<A> abilityClass) {
		return findAbility(abilityClass::isInstance).map(abilityClass::cast);
	}

	public Stream<Ability> findAbilityList(Predicate<Ability> predicate) {
		return abilities.stream().filter(predicate);
	}

	public <A extends Ability> Stream<A> findAbilityList(Class<A> abilityClass) {
		return findAbilityList(abilityClass::isInstance).map(abilityClass::cast);
	}

}
