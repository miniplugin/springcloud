package com.boot.configserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
class ConfigserverApplicationTests {
	private Log log = LogFactory.getLog(getClass());
	//스프링 부트2용: 스프링 부트1 일때는 Configurable 대신 ApplicationContext 객체 사용
	@Autowired
	private ConfigurableApplicationContext configurableApplicationContext;

	@Test
	void contextLoads() {
		Object kimilguk = null;//강제로 assertNotNull 메서드 에러상황 만들때 확인용
		//JUnit5 용 JUnit4 일때는 Assert.assertNotNull()사용
		Assertions.assertNotNull(this.configurableApplicationContext);
		Assertions.assertAll("assertAll 은 에러가 발생해도 바로 중지 하지 않고, 아래 모든 메서드를 run 한다",
				() -> {
					Assertions.assertFalse(false, String.valueOf(this.configurableApplicationContext));
					log.info("assertFalse 실행");//위 false 를 true 로 변경해서 실행해 보세요
				},
				() -> {
					Assertions.assertTrue(true, String.valueOf(this.configurableApplicationContext));
					log.info("assertTrue 실행");
				},
				() -> {
					Assertions.assertNotNull(this.configurableApplicationContext);
					log.info("assertNotNull 실행");
				}
			);
	}

}
