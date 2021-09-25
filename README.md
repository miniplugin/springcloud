### 스프링 클라우드용 프로젝트 여러개를 1개의 깃으로 병합
- 기존 springboot2(인텔리 J) 와 configserver(이클립스) 프로젝트를 1개의 저장소로 통합
- 통합한 것을 인텔리 J 로 불러옴: 인텔리 J 에서 2개가 모두 동시에 실행된 모습(아래)
  ![ex_screenshot](./README/img.png)
- git submodule add https://github.com/miniplugin/cloud-config-server-configuration.git
- 위 명령으로 현재 깃 저장소에 클라우드용 설정 내용 가져오기처리
- 주) 위 가져온 깃 내용은 확인용으로 직접 수정은 않됨, 수정하려면, 위 외부 깃 주소내용을 수정해야 함

#### 20210925(토)
- 스프링 클라우드 클라이언트 앱 jar 생성
- 외부 Config 서버 설정: https://github.com/cloud-native-java/config-server-configuration-repository
- 스프링 클라우드 설정 소스참조: https://github.com/cloud-native-java/configuration
