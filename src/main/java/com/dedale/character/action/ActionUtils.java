package com.dedale.character.action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

class ActionUtils {

	@SafeVarargs
	public final <T> boolean isSatisfiedBy(T t, Predicate<T>... restrictions) {
		return isSatisfiedBy(t, Arrays.asList(restrictions));
	}

	public final <T> boolean isSatisfiedBy(T t, Iterable<Predicate<T>> restrictions) {
		Optional<T> optAction = Optional.ofNullable(t);
		Iterator<Predicate<T>> restrictionsIterator = restrictions.iterator();
		while (optAction.isPresent() && restrictionsIterator.hasNext()) {
			optAction = optAction.filter(restrictionsIterator.next());
		}
		return optAction.isPresent();
	}

}
