package com.java.basic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Part_04_TestNG_Annotation_2 {
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After test");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("---Before class---");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("---After class---");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("--***Before Method***--");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("--***After Method***--");
	}

	@Test(description="The first test case")
	public void Test_01() {
		System.out.println("Test01");
	}

	@Test(description="The second test case")
	public void Test_02() {
		System.out.println("Test02");
	}

	@Test(description="The third test case")
	public void Test_03() {
		System.out.println("Test03");
	}
}
