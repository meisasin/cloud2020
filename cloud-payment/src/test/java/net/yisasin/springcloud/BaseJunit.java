package net.yisasin.springcloud;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class BaseJunit {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseJunit.class);

	@Before
	public void setUp() throws Exception {

		logger.info("\n\n -------------------- 测试方法执行开始[{}]   --------- from testClass : {} --------------------\n",
				LocalDateTime.now(), this.getClass().getSimpleName());
	}

	@After
	public void tearDown() throws Exception {
		logger.info("\n\n -------------------- 测试方法执行结束[{}]   --------- from testClass : {} --------------------\n",
				LocalDateTime.now(), this.getClass().getSimpleName());
	}
	
	
}
