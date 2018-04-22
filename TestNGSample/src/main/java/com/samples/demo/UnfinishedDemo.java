package com.samples.demo;

import com.samples.myannotations.Unfinished;
import com.samples.myannotations.Unfinished.Priority;

@Unfinished(value = "Sample class scope use",
			priority = Priority.VERY_LOW)
public class UnfinishedDemo {
	
	@Unfinished("Unfinished constructor")
	public UnfinishedDemo() { }
	
	@Unfinished(value = "Unfinished method",
				owners = {"Bob"})
	public void someMethod() { }
}
