/**
 * 
 */
package com.cobee.core.common.persistence.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
/** 
 * <pre>标记MyBatis创建Mapper映射器的时候注入哪个数据源</pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月10日
 *
 */
public @interface DefaultDataSource {

}
