package com.samples.testng.demo;

import com.samples.testng.annotation.ComplexAnnotation;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;


@ComplexAnnotation
public class SampleClass {

//	public static void main(String[] args) {
//		WebDriver driver = new FirefoxDriver();
//		driver.get("http://www.facebook.com");
//		driver.close();
//	}
	
	public boolean sampleMethod(boolean value) {
		return value;
	}

}
