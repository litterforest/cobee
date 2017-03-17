/**
 * 
 */
package com.cobee.admin.common.util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

/** <pre>生成唯一性ID算法的工具类</pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年3月17日
 *
 */
public class CobeeIdGenerator implements  SessionIdGenerator {


	private static SecureRandom random = new SecureRandom();
	
	/**
	 * JDK自带的UUID, 返回不带-符号的字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	/*public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		// return Encodes.encodeBase62(randomBytes);
		return new String(randomBytes);
	}*/
	

	@Override
	public Serializable generateId(Session session) {
		return CobeeIdGenerator.uuid();
	}

}
