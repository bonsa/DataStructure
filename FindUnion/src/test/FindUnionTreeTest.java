package test;

import main.FindUnion;
import main.FindUnionTreeImpl;

public class FindUnionTreeTest extends FindUnionTestCase {

	@Override
	FindUnion<Integer> getFindUnionInstance() {
		return new FindUnionTreeImpl<Integer>();
	}

}
