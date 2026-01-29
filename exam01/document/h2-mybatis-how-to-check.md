H2 및 MyBatis 도입 설정 완료 보고서
Spring Boot 프로젝트에 데이터 영속성 관리를 위한 H2 데이터베이스와 MyBatis SQL 매퍼 설정을 완료했습니다.

변경 내용
1. 의존성 추가
build.gradle
에 필요한 라이브러리를 추가했습니다.

H2 Database (런타임 의존성)
MyBatis Spring Boot Starter (3.0.4 버전)
2. 환경 설정
application.yml
을 통해 데이터베이스 및 매퍼 설정을 완료했습니다.

H2 DB: 파일 모드(jdbc:h2:file:./data/testdb) 및 H2 Console 활성화
MyBatis: 매퍼 XML 위치 지정, 별칭 패키지(com.example.vibeapp.post) 설정, 카멜 케이스 자동 변환 활성화
디버깅: Spring Boot 자동 설정 디버그 로그 활성화 및 파일 로그(app.log) 설정
3. 버전 관리 설정
.gitignore
에 로컬 DB 관련 파일들을 제외하여 저장소의 청결함을 유지했습니다.

정상 동작 확인 방법
1. H2 Console 접속
애플리케이션 실행 후 웹 브라우저에서 다음 주소로 접속합니다.

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/testdb
User Name: sa (Password 생략)
2. 로그 확인
프로젝트 루트의 ./logs/app.log 파일을 확인하여 데이터베이스 커넥션 및 MyBatis 초기화 로그가 정상적으로 출력되는지 확인합니다.

3. 디버그 모드
애플리케이션 시작 시 콘솔 창에 출력되는 CONDITIONS EVALUATION REPORT를 통해 H2 및 MyBatis 관련 자동 설정들이 정상적으로 적용되었는지 확인할 수 있습니다.