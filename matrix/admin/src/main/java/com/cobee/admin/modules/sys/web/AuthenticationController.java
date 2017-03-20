/**
 * 
 */
package com.cobee.admin.modules.sys.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cobee.admin.common.web.BaseController;

/**
 * <pre>认证控制器，有用户登录功能</pre>
 *
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年3月19日 下午3:57:44
 *
 */
@Controller
public class AuthenticationController extends BaseController {

	private static final transient Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	/**
	 * <pre>登录页面</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年3月19日 下午4:17:19
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "login")
	public String login() throws Exception
	{
		return "modules/sys/login";
	}
	
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String doLogin(String username, String password, Model model) throws Exception
	{
		Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try 
            {
                currentUser.login(token);
            }
            catch (AuthenticationException e) {
            	logger.error("", e);
            	model.addAttribute("msg", e.getMessage());
            	return "modules/sys/login";
            }
        }
		return "redirect:/";
	}
	
	/**
	 * <pre>登录成功后跳转的页面</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年3月19日 下午4:17:32
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "framework")
	public String framework() throws Exception
	{
		return "modules/sys/framework";
	}
	
	@RequestMapping(value = "welcome")
	public String welcome() throws Exception
	{
		return "modules/sys/welcome";
	}
	
	
	
}
