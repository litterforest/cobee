/**
 * 
 */
package com.cobee.core.common.persistence;

/** <pre>返回应用中具体的授权者信息</pre>
 *
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月11日 下午9:45:05
 *
 */
public interface AuditorAware {

	String getCurrentAuditor();
	
}
