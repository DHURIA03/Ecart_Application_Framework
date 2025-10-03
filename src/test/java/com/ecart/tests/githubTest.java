package com.ecart.tests;


import org.testng.annotations.Test;

public class githubTest 
{
	@Test(priority=1)
	public void validateGitTestA() 
	{
		System.out.println("This is new testcase");
	}
	
	@Test(priority=2)
	public void validateGitTestB() 
	{
		System.out.println("This is new testcase testcase B");
	}
	
	@Test(priority=3)
	public void validateGitTestC() 
	{
		System.out.println("This is new testcase testcase C modified at branch Ashish");
	}

}
