/**
 * 
 */
package admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
	
//	@Autowired
//	private SysOfficeService sysOfficeService;
	
	@Test
	public void test1()
	{
//		SysOffice sysOffice = new SysOffice();
//		PageRequest pageRequest = new PageRequest(1, 5);
//		sysOffice.setPageRequest(pageRequest);
////		sysOffice.setName("财务部");
//		sysOfficeService.findByPage(sysOffice);
	}
	
}
