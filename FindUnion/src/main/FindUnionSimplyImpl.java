package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUnionSimplyImpl<T> implements FindUnion<T> {

	Map<T, T> elementToRepresentative;
	Map<T, List<T>> representativeToSet;

	public FindUnionSimplyImpl() {
		elementToRepresentative = new HashMap<T, T>();
		representativeToSet = new HashMap<T, List<T>>();
	}

	@Override
	public void makeSet(T x) {
		if (elementToRepresentative.containsKey(x)) {
			throw new FindUnionException(String.format("Structure contains %s.", x));
		}
		List<T> listContainingX = new ArrayList<T>();
		listContainingX.add(x);
		representativeToSet.put(x, listContainingX);
		elementToRepresentative.put(x, x);
	}

	@Override
	public T find(T x) {
		if (!elementToRepresentative.containsKey(x)) {
			throw new FindUnionException(String.format("Structure doesn't contain %s.", x));
		}
		return elementToRepresentative.get(x);
	}

	@Override
	public void union(T x, T y) {
		T repForX = find(x);
		T repForY = find(y);
		if (repForX.equals(repForY)) {
			return;
		}

		List<T> listX = representativeToSet.get(repForX);
		List<T> listY = representativeToSet.get(repForY);

		for (T element : listY) {
			elementToRepresentative.replace(element, repForX);
		}

		if (listX.size() > listY.size()) {
			listY.addAll(listX);
			representativeToSet.replace(repForX, listY);
		} else {
			listX.addAll(listY);
		}

		representativeToSet.remove(repForY);
	}


}
