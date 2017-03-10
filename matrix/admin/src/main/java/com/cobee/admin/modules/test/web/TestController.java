/**
 * 
 */
package com.cobee.admin.modules.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre></pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年3月7日
 *
 */
@Controller
@RequestMapping("Test")
public class TestController {
	
	@RequestMapping(value="test")
	public String test() throws Exception
	{
		return "modules/test/test";
	}
	
}
