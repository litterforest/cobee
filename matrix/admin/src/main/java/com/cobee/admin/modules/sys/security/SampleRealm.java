/**
 * 
 */
package com.cobee.admin.modules.sys.security;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Sha512CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/** <pre>简单的用户认证数据处理例子</pre>
 *
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年3月19日 下午8:52:48
 *
 */
public class SampleRealm extends AuthorizingRealm {

	/**
	 * 
	 * @param token 封装登录页面中的用户名和密码数据
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[SampleRealm]:doGetAuthenticationInfo");
		// 1, 把AuthenticationToken强制性转换为UsernamePasswordToken对象
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 2, 获取用户名称
		String username = upToken.getUsername();
		// 3, 根据username到数据库中查找用户记录
		String credentials = null;
		
		// 4, 用户合法性验证
		if ("unknow".equals(username))
		{
			throw new UnknownAccountException("用户名或密码不对");
		}
		if ("monster".equals(username))
		{
			throw new LockedAccountException("用户已被锁定，暂时不能使用");
		}
		if ("admin".equals(username))
		{
			credentials = "612f1c06e2092d739c04fb00588c5b940889afb18b69bd2b83de02edfc8caf1158f8f904de50c81aa83e3388724eb97b16fdf8d211a0f8840f7f3fe116e7534b";
		}
		// 5, 返回AuthenticationInfo对象,参数使用数据库中数据，shiro会根据这个对象与传进来的token对象进行比对。
		ByteSource salt = ByteSource.Util.bytes("admin");
		AuthenticationInfo authenticationInfo = null;
		authenticationInfo = new SimpleAuthenticationInfo(username, credentials, salt, getName());
		return authenticationInfo;
	}

	public static void main(String[] args) {
		ByteSource salt = ByteSource.Util.bytes("admin");
		SimpleHash sh = new SimpleHash("SHA-512", "123456", salt, 31);
		System.out.println(sh.toBase64());
		System.out.println(sh.toHex());
		System.out.println(sh.toString());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		// 1,获取用户信息
		Object principal = pc.getPrimaryPrincipal();
		// 2,查找用户相关的角色和权限
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if ("admin".equals(principal))
		{
			roles.add("admin");
		}
		// 3,返回SimpleAuthorizationInfo对象
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		sai.setRoles(roles);
		return sai;
	}
	
}
