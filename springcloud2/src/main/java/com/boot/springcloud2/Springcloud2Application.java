package com.boot.springcloud2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
//스프링부트 애플리케이션임을 명시(아래)
@SpringBootApplication
public class Springcloud2Application {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	//스프링부트 애플리시케이션 실행 진입점 main(아래)
	public static void main(String[] args) {
		SpringApplication.run(Springcloud2Application.class, args);
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("prod");
		annotationConfigApplicationContext.register(Springcloud2Application.class);
		annotationConfigApplicationContext.refresh();
	}
	@Autowired
	public Springcloud2Application(ConfigurationProjectProperties configurationProjectProperties) {
		logger.info("yaml 개발설정 projectName: "+ configurationProjectProperties.getProjectName());
	}
}

@Component
@ConfigurationProperties("configuration")
class ConfigurationProjectProperties {
	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}

@RestController
class Web {
	@Autowired
	private CatRepository catRepository;
	@GetMapping("/cats_list")
	public List<Cat> web() {
		Stream.of("고양이1","cat2","고양이3")
				.forEach(n -> catRepository.save(new Cat(n)));
		List<Cat> resultMap = catRepository.findAll();
		return resultMap;
	}
	/*public ResponseEntity<Object> web() {
		ResponseEntity<Object> result = null;
		List<Cat> resultMap = catRepository.findAll();
		result = new ResponseEntity<Object>(resultMap, HttpStatus.OK);
		return result;
	}*/
}
//Jpa 엔티티 클래스(롬복사용 아래)
@NoArgsConstructor//Constructor Injection 방식: @RequiredArgsConstructor
@Getter
@ToString
@Entity
class Cat {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	//public Cat() { };
	public Cat(String name) {
		this.name = name;
	}
	/*
	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + "]";
	}
	*/
}
//DAO 인터페이스: CRUD 를 처리해주며, Rest API로 외부에 공개되는 스프링 데이터 Jpa 레포지토리
@RepositoryRestResource
interface CatRepository extends JpaRepository<Cat, Long> {
	
}