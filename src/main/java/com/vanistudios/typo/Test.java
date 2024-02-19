package com.vanistudios.typo;

public class Test {
	
	
	final static int nature = 11;
	
	int testName;

	public Test(int a) {
		this.testName = a;
	}
	
	
	public static void main(String...args)
	{
		Test t1 = new Test(10);
		System.out.println(t1.testName);

		System.out.println(Test.nature);
	}	
	
}