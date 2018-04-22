package com.samples.testng.annotation;

import java.lang.annotation.*;


@Unfinished("Just articleware")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE })
@Inherited
public @interface Unfinished {
	public enum Priority { VERY_LOW, LOW, NORMAL, URGENT }
	
	String value();
	String[] owners() default {"Aidela"};
	Priority priority() default Priority.NORMAL;
}
