package com.countgandi.analye.league.match;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NameAnnotation {
	public String name() default "";
	public String[] nameArray() default {"", ""};
	public boolean space() default false;
}
