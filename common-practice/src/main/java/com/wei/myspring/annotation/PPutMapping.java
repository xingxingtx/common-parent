/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wei.myspring.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author a_pen
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PRequestMapping(method = RequestMethod.PUT)
public @interface PPutMapping {

	/**
	 * Alias for {@link PRequestMapping#name}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
    String name() default "";

	/**
	 * Alias for {@link PRequestMapping#value}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
	String[] value() default {};

	/**
	 * Alias for {@link PRequestMapping#path}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
	String[] path() default {};

	/**
	 * Alias for {@link PRequestMapping#params}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
	String[] params() default {};

	/**
	 * Alias for {@link PRequestMapping#headers}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
	String[] headers() default {};

	/**
	 * Alias for {@link PRequestMapping#consumes}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
	String[] consumes() default {};

	/**
	 * Alias for {@link PRequestMapping#produces}.
	 */
	@AliasFor(annotation = PRequestMapping.class)
	String[] produces() default {};

}
