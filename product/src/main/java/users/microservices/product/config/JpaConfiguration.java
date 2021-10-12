package users.microservices.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 비어 있는 경우 JPA 감사 사용
 * Java에서 ORM 기술인 JPA를 사용하여 도메인을 관계형 데이터베이스 테이블에 매핑할 때 공통적으로 도메인들이 가지고 있는 필드
 * 여기서는 data/BaseEntity 의 필드를 자동으로 공통 생성해 준다.
 */
@Configuration
@EnableJpaAuditing
public class JpaConfiguration {
}