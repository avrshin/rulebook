package com.deliveredtechnologies.rulebook.annotation;

import com.deliveredtechnologies.rulebook.Decision;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Rule defines a class to be injected into a {@link Decision}.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Rule {

  /**
   * This is the name of the Rule.
   */
  String name() default "None";

  /**
   * This specifies the order in which the rule will execute.
   * The ordering is 1, 2, 3, 4, etc.
   * Two rules in the same package with the same order will execute in a non-specified order.
   */
  int order() default 1;
}
