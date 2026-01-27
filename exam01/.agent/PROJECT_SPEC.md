# 프로젝트 명세서 (Project Specification)

이 문서는 스프링부트(Spring Boot) 기반의 최소 기능 웹 애플리케이션 프로젝트인 **vibeapp**의 기술 명세 및 설정 정보를 포함합니다.

## 1. 프로젝트 개요
- **프로젝트명**: vibeapp
- **설명**: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트입니다.
- **주요 목적**: Spring Boot 4.0.1 및 Java 25 기반의 표준화된 스타터 환경 구축.

## 2. 프로젝트 메타데이터 (Metadata)
| 항목 | 값 |
| :--- | :--- |
| **Group** | `com.example` |
| **Artifact** | `vibeapp` |
| **Main Class Name** | `VibeApp` |
| **Description** | 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다. |
| **Package Name** | `com.example.vibeapp` |

## 3. 기술 스택 (Technical Stack)
- **Language**: Java
- **JDK Version**: JDK 25 이상 (Toolchain 설정 완료)
- **Framework**: Spring Boot 4.0.1+ (Web, Thymeleaf)
- **View Engine**: Thymeleaf
- **Build Tool**: Gradle 9.3.0+
- **Configuration Format**: YAML (`application.yml`)

## 4. 빌드 및 종속성 관리 (Build & Dependencies)
### Gradle 설정
- **DSL**: Groovy DSL (`build.gradle`)
- **버전**: Gradle 9.3.0 이상
- **Wrapper**: `gradlew` 설정 완료 (윈도우/맥/리눅스 호환)

### 주요 플러그인
- `org.springframework.boot` (Spring Boot 기반 설정)
- `io.spring.dependency-management` (Spring Boot 버전에 맞춘 의존성 관리)
- `java` (Java 프로젝트 지원)

### 종속성 (Dependencies)
- `spring-boot-starter-web`: REST API 구현을 위한 웹 스타터
- `spring-boot-starter-thymeleaf`: HTML 뷰 렌더링을 위한 템플릿 엔진
- `spring-boot-starter-test`: 테스트 환경 구축
- `junit-platform-launcher`: JUnit 5 테스트 실행 지원

## 5. 현재 구현 상태 (Current Implementation)
- **Web View**:
    - `/` (Home): `index.html` (Thymeleaf) 렌더링
- **REST API**:
    - `GET /api/hello`: "Hello, Vibe!" 문자열 반환
- **빌드 검증**:
    - `gradlew.bat build` 명령어를 통해 빌드 성공 확인 (2026-01-27)

## 6. 설정 정보 (Configuration)
- **설정 파일**: `src/main/resources/application.yml`
- **형식**: YAML

---
*마지막 업데이트: 2026-01-27*
