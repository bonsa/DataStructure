package test;

import main.FindUnion;
import main.FindUnionSimplyImpl;

public class FindUnionSimplyTest extends FindUnionTestCase{

	@Override
	FindUnion<Integer> getFindUnionInstance() {
		return new FindUnionSimplyImpl<Integer>();
	}

}
