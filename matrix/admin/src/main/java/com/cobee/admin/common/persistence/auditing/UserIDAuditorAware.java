/**
 * 
 */
package com.cobee.admin.common.persistence.auditing;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobee.core.common.persistence.AuditorAware;

/** <pre></pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月12日
 *
 */
public class UserIDAuditorAware implements AuditorAware {

	/* (non-Javadoc)
	 * @see com.cobee.core.common.persistence.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public String getCurrentAuditor() {
		Subject subject = SecurityUtils.getSubject();
		subject.getPrincipal();
		return "";
	}

}
