/**
 * 
 */
package admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cobee.core.domain.PageRequest;
import com.cobee.core.entity.sys.SysUser;
import com.cobee.core.service.sys.SysUserService;

/** <pre>基础测试</pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月6日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@WebAppConfiguration
public class BaseTester {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void test1()
	{
		SysUser sysUser = new SysUser();
		PageRequest pageRequest = new PageRequest(3, 5);
		pageRequest.setOrderByClause(" ORDER BY a.create_by desc ");
		sysUser.setPageRequest(pageRequest);
//		sysUser.setName("财务部");
		sysUserService.findByPage(sysUser);
	}
	
}
