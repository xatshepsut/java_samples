package com.samples.testng.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.samples.testng.annotation.ComplexAnnotation;
import com.samples.testng.demo.SampleClass;
//import com.samples.testng.listener.SampleTestListener;
import com.samples.testng.listener.SampleTestListener;

//@Listeners(SampleTestListener.class)
@ComplexAnnotation(listener = @Listeners(SampleTestListener.class), value = "")
@Listeners(SampleTestListener.class)
public class TestSampleClass_2 {
	
	@Test()
	public void test() {
		boolean value = false;
		
		SampleClass object = new SampleClass();
		boolean result = object.sampleMethod(value);
		
		Assert.assertEquals(result, value);
	}

}
