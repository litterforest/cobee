package admin;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTester {
	
	private static final Logger logger = LoggerFactory.getLogger(Log4jTester.class);
	
	@Test
	public void test1()
	{
		logger.debug("DEBUG");
		logger.info("INFO");
		logger.warn("WARN");
		logger.error("ERROR");
	}
	
}
