### 스프링 클라우드용 프로젝트 여러개를 1개의 깃으로 병합
- 기존 springboot2(인텔리 J) 와 configserver(이클립스) 프로젝트를 1개의 저장소로 통합
- 통합한 것을 인텔리 J 로 불러옴: 인텔리 J 에서 2개가 모두 동시에 실행된 모습(아래)
  ![ex_screenshot](./README/img.png)
- git submodule add https://github.com/miniplugin/cloud-config-server-configuration.git
- 위 명령으로 현재 깃 저장소에 클라우드용 설정 내용 가져오기처리
- 주) 위 가져온 깃 내용은 확인용으로 직접 수정은 않됨, 수정하려면, 위 외부 깃 주소내용을 수정해야 함
- 외부 작업결과는 Git 메뉴의 Update Project 클릭하면 적용 됨.
- 스프링 이니셜라이즈로 스프링부트 생성 후 인텔리 J 로 불러와서 pom.xml 파일 우클릭 > add Maven Project 선택하면 실행가능하게 변함.

#### 20210926(일)
- @RefreshScope 붙은 클래스 객체는 실행시 설정서버 정보를 항상 새로 불러 들인다.
- RefreshCounter 로 위 @RefreshScope 가 붙은 ProjectNameRestController 스프링 빈의 리프레시를 확인
- 3장 마무리: 주) configclient 앱 실습은 클라우드 파운드리(PaaS-TA) 에 배포 했을때 사용 가능 합니다.
- 클라우드버스 와 래빗엠큐 로 메시징 데이터를 전달하기 위해서 pom.xml 에 아래 내용 추가 한다.
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-bus</artifactId>
</dependency>
```

#### 20210925(토)
- 액츄에이터 메이븐 라이브러리: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
- Config 서버 프로젝트 추가(아래)
  ![ex_screenshot](./README/springcloud5.jpg)
- 결과 확인: http://127.0.0.1:8888/configuration-client/master
- 스프링 클라우드 클라이언트 앱 jar 생성(이클립스일때, 인텔리 J 일때는 바로 실행 가능)
- 외부 Config 서버 설정: https://github.com/cloud-native-java/config-server-configuration-repository
- 스프링 클라우드 설정 소스참조: https://github.com/cloud-native-java/configuration
- Config 클라이언트 프로젝트 추가(아래): 아직 PaaS 에 배포하지 않아서 vcap.service 르 configserver 값을 import 할 수 없음.
- 참고: VCAP(VMWare Cloud Application Platform) 은 클라우드 파운드리(CF) 의 플래폼을 말합니다.
  ![ex_screenshot](./README/img_1.png)
