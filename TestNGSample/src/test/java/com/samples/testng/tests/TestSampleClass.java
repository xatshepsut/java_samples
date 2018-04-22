package com.samples.testng.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSampleClass {
	
	@Test()
	public void test() {
		boolean value = false;
		
		SampleClass object = new SampleClass();
		boolean result = object.sampleMethod(value);
		
		Assert.assertEquals(result, !value);
	}

}
