package com.boot.springcloud2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * 이 클래스는 환경설정 을 xml 이 아닌 자바방식으로 사용할때 외부 property 변수를 사용하는 방법을 보여줍니다.
 */
@Configuration
@ConfigurationProperties("configuration")//yaml 설정 사용하기 위해서 추가
//@Profile("default")//default 일때는 필요없음:application.properties 자동으로 읽어 들임
public class Springboot2Config {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Configuration
    @Profile("dev")
    @PropertySource("application_dev.properties")
    class DevConfiguration {
        @Bean
        InitializingBean init() {
            return () -> logger.info("dev 빈 초기화");
        }
    }

    @Configuration
    @Profile("prod")
    @PropertySource("application_prod.properties")
    class ProdConfiguration {
        @Bean
        InitializingBean init() {
            return () -> logger.info("prod 빈 초기화");
        }
    }

    /* 필요없음: 자동으로 PropertySource 읽어 들임
    @Bean
    static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    */

    @Value("${configuration.projectName}")
    private String projectName;

    @Value("${configuration.projectName}")
    public void setProjectName(String projectName) {
        logger.info("셋 매개변수: " + projectName);
    }

    @Autowired//yaml 변수 값 가져오기
    Springboot2Config(@Value("${configuration.projectName}") String projectName) {
        logger.info("생성자 매개변수: " + projectName);
    }

    @Autowired
    void setEnvironment(Environment environment) {
        logger.info("환경객체 매개변수: " + environment.getProperty("configuration.projectName"));
    }

    @Bean
    InitializingBean both(Environment environment, @Value("${configuration.projectName}") String projectName) {
        return () -> {
            logger.info("@빈의 메서드파라미터 projectName: " + projectName);
            logger.info("@빈의 메서드파라미터 환경객체: " + environment.getProperty("configuration.projectName"));
        };
    }

    @PostConstruct
    void afterPropertiesSet() throws Throwable {
        logger.info("생성자 매서드 실행 이후: " + this.projectName);
    }
}

