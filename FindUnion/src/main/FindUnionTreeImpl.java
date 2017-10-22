package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUnionTreeImpl<T> implements FindUnion<T> {

	private Map<T, Node<T>> intToNode;

	public FindUnionTreeImpl() {
		intToNode = new HashMap<T, FindUnionTreeImpl.Node<T>>();
	}

	@Override
	public void makeSet(T x) {
		if (intToNode.containsKey(x)) {
			throw new FindUnionException(String.format(
					"Structure contains %s.", x));
		}

		Node<T> node = new Node<T>(null, 0, x);
		intToNode.put(x, node);
	}

	@Override
	public T find(T x) {

		if (!intToNode.containsKey(x)) {
			throw new FindUnionException(String.format(
					"Structure doesn't contain %s.", x));
		}

		Node<T> node = intToNode.get(x);
		List<Node<T>> nodePath = new ArrayList<Node<T>>();
		while (node.parent != null) {
			if (node.parent.parent != null) {
				nodePath.add(node);
			}
			node = node.parent;
		}

		for (Node<T> nodeFromPath : nodePath) {
			nodeFromPath.parent = node;
		}

		return node.value;
	}

	@Override
	public void union(T x, T y) {
		T representantX = find(x);
		T representantY = find(y);
		if (representantX.equals(representantY)) {
			return;
		}
		Node<T> nodeRepresentantX = intToNode.get(representantX);
		Node<T> nodeRepresentantY = intToNode.get(representantY);

		int rankX = nodeRepresentantX.rank;
		int rankY = nodeRepresentantY.rank;

		if (rankX == rankY) {
			nodeRepresentantX.rank++;
		}
		if (rankX >= rankY) {
			nodeRepresentantY.parent = nodeRepresentantX;
		} else {
			nodeRepresentantX.parent = nodeRepresentantY;
		}

	}

	private static class Node<T> {
		private Node<T> parent;
		private int rank;
		private final T value;

		public Node(Node<T> parent, int rank, T value) {
			super();
			this.parent = parent;
			this.rank = rank;
			this.value = value;
		}
	}

}
