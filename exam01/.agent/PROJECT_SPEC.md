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
- **UI Framework**: Bootstrap 5 (via CDN)
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
- `spring-boot-starter-web`: REST API 및 웹 개발을 위한 핵심 스타터
- `spring-boot-starter-thymeleaf`: HTML 뷰 렌더링을 위한 템플릿 엔진
- `spring-boot-starter-test`: 테스트 환경 구축
- `junit-platform-launcher`: JUnit 5 테스트 실행 지원

## 5. 현재 구현 상태 (Current Implementation)
### 주요 컨트롤러 (Key Controllers)
- **`HomeController`**: 애플리케이션의 메인 홈 페이지 요청 처리 (`home/home.html`)
- **`PostController`**: 게시판 기능(목록, 상세, 등록, 수정, 삭제) 관련 웹 요청 처리
- **`VibeApp`**: 애플리케이션의 진입점(Entry Point) 클래스

### 서비스 및 데이터 (Services & Data)
- **`PostService`**: 게시판 비즈니스 로직(조회수 증가, 페이징 계산 등) 처리
- **`PostRepository`**: 인메모리(`ArrayList`) 기반의 게시글 데이터 저장소

### 엔드포인트 및 기능 (Web View)
- `/` (Home): 홈 페이지 (서버 시간 출력 및 대시보드 형태)
- `/posts`: 게시글 목록 (페이지당 5개 페이징 지원)
- `/posts/{no}`: 게시글 상세 내용 및 조회수 표시
- `/posts/new`: 게시글 작성 폼
- `POST /posts/add`: 게시글 등록 처리 프로세스
- `/posts/{no}/edit`: 게시글 수정 폼
- `POST /posts/{no}/save`: 게시글 수정 처리 프로세스
- `/posts/{no}/delete`: 게시글 삭제 처리 프로세스
- `/index.html`: 가이드 및 서버 정보 확인 페이지

### 빌드 및 검증 상태
- **빌드 도구**: Gradle Wrapper (`.\gradlew.bat`)
- **검증 완료**: `gradlew build` 성공 (최종 확인: 2026-01-28)
- **디자인**: Tailwind CSS 및 Bootstrap 5를 활용한 현대적인 UI/UX 적용

## 6. 프로젝트 디렉터리 구조 (Project Directory Structure)
```text
.
├── .agent/
│   └── PROJECT_SPEC.md        # 프로젝트 명세서
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/vibeapp/
│       │       ├── VibeApp.java         # 메인 애플리케이션 클래스
│       │       ├── home/
│       │       │   └── HomeController.java # 홈 페이지 컨트롤러
│       │       └── post/
│       │           ├── Post.java         # 게시글 엔티티
│       │           ├── PostController.java # 게시글 컨트롤러
│       │           ├── PostRepository.java # 게시글 저장소
│       │           └── PostService.java    # 게시글 서비스
│       └── resources/
│           ├── templates/
│           │   ├── home/
│           │   │   └── home.html       # 홈 페이지 템플릿
│           │   ├── post/               # 게시글 관련 템플릿들
│           │   └── index.html          # 가이드 페이지 템플릿
│           └── application.yml         # 애플리케이션 설정 파일
├── build.gradle                # Gradle 빌드 스크립트
└── README.md                   # 프로젝트 리드미
```

## 7. 설정 정보 (Configuration)
- **설정 파일**: `src/main/resources/application.yml`
- **형식**: YAML

---
*마지막 업데이트: 2026-01-28 (코드 리팩토링 및 네이밍 정제 반영)*
