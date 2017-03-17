/**
 * 
 */
package com.cobee.admin.modules.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cobee.admin.common.web.BaseController;

/** <pre>首页控制器</pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年3月15日
 *
 */
@Controller
@RequestMapping("index")
public class IndexController extends BaseController {
	
	@RequestMapping(value = "")
	public String index() throws Exception
	{
		return "modules/index/index";
	}
	
	@RequestMapping(value = "topMenu")
	public String topMenu() throws Exception
	{
		return "modules/index/top-menu";
	}
	
	@RequestMapping(value = "jqueryUI")
	public String jqueryUI() throws Exception
	{
		return "modules/index/jquery-ui";
	}
	
	@RequestMapping(value = "contentIndex")
	public String contentIndex() throws Exception
	{
		return "modules/index/contentIndex";
	}
	
}
