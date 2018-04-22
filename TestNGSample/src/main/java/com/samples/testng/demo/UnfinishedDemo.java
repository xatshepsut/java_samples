package com.samples.testng.demo;

import com.samples.testng.annotation.Unfinished;
import com.samples.testng.annotation.Unfinished.Priority;

@Unfinished(value = "Sample class scope use",
			priority = Priority.VERY_LOW)
public class UnfinishedDemo {
	
	@Unfinished("Unfinished constructor")
	public UnfinishedDemo() { }
	
	@Unfinished(value = "Unfinished method",
				owners = {"Bob"})
	public void someMethod() { }
}
