package com.samples.testng.annotation;

import java.lang.annotation.*;

import org.testng.annotations.Listeners;

import com.samples.testng.listener.SampleTestListener;


@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComplexAnnotation {

	String value() default "";
	Listeners listener() default @Listeners(SampleTestListener.class);
	Deprecated depriciated() default @Deprecated;
}
