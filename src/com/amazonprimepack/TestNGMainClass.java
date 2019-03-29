package com.amazonprimepack;

import org.testng.TestNG;

public class TestNGMainClass {

	public static void main(String[] args) {
		TestNG testSuite = new TestNG();
		testSuite.setTestClasses(new Class[] { TestBase.class });
		testSuite.setDefaultSuiteName("My Test Suite");
		testSuite.setDefaultTestName("My Test");
		testSuite.run();
	}

}