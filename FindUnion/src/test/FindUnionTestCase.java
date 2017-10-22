package test;

import static org.assertj.core.api.Assertions.assertThat;
import main.FindUnion;
import main.FindUnionException;

import org.junit.Test;

public abstract class FindUnionTestCase {

	abstract FindUnion<Integer> getFindUnionInstance();

	@Test
	public void unionTwoElements() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		findUnion.makeSet(1);
		findUnion.makeSet(2);
		// when
		findUnion.union(1, 2);
		// then
		assertThat(findUnion.find(2)).isIn(1,2);
	}

	@Test
	public void unionMultipleElementsIntoOneSet() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		findUnion.makeSet(1);
		findUnion.makeSet(2);
		findUnion.makeSet(3);
		findUnion.makeSet(4);
		findUnion.makeSet(5);
		findUnion.makeSet(6);
		// when
		findUnion.union(6, 5);
		findUnion.union(4, 3);
		findUnion.union(2, 1);
		findUnion.union(1, 4);
		findUnion.union(1, 6);
		// then
		int representant = findUnion.find(1);
		assertThat(findUnion.find(2)).isEqualTo(representant);
		assertThat(findUnion.find(3)).isEqualTo(representant);
		assertThat(findUnion.find(4)).isEqualTo(representant);
		assertThat(findUnion.find(5)).isEqualTo(representant);
		assertThat(findUnion.find(6)).isEqualTo(representant);
	}

	@Test
	public void unionSetsWithDiffrentLength() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		findUnion.makeSet(1);
		findUnion.makeSet(2);
		findUnion.makeSet(3);
		findUnion.makeSet(4);
		findUnion.makeSet(5);
		findUnion.makeSet(6);
		findUnion.union(6, 5);
		findUnion.union(4, 3);
		findUnion.union(2, 1);
		findUnion.union(1, 4);
		// when
		findUnion.union(6, 1);
		// then
		int representant = findUnion.find(1);
		assertThat(findUnion.find(2)).isEqualTo(representant);
		assertThat(findUnion.find(3)).isEqualTo(representant);
		assertThat(findUnion.find(4)).isEqualTo(representant);
		assertThat(findUnion.find(5)).isEqualTo(representant);
		assertThat(findUnion.find(6)).isEqualTo(representant);
	}

	@Test
	public void unionTwoTheSameElements() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		findUnion.makeSet(1);
		// when
		findUnion.union(1, 1);
		// then
		assertThat(findUnion.find(1)).isEqualTo(1);
	}

	@Test
	public void unionElementsFromTheSameSet() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		findUnion.makeSet(1);
		findUnion.makeSet(2);
		findUnion.union(1, 2);
		// when
		findUnion.union(1, 2);
		// then
		int representant = findUnion.find(1);
		assertThat(findUnion.find(2)).isEqualTo(representant);
	}	

	@Test(expected = FindUnionException.class)
	public void findForMissingElementShouldThrowException() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		// when
		findUnion.find(1);
	}

	@Test(expected = FindUnionException.class)
	public void makeSetForExistingElementShouldThrowException() {
		// setup
		FindUnion<Integer> findUnion = getFindUnionInstance();
		findUnion.makeSet(1);
		// when
		findUnion.makeSet(1);
	}
}
