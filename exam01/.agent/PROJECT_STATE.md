# 프로젝트 명세서 (PROJECT_STATE.md)

## 프로젝트 개요
- **프로젝트 명**: VibeApp
- **기술 스택**: Java 21, Spring Boot 4.0.1, Gradle
- **주요 기능**: 간단한 REST API 제공 ("Hello, Vibe!")

## 현재 상태
- **빌드 시스템**: `build.gradle` 설정 완료 (Spring Boot Web, Test 의존성 포함)
- **주요 소스**: `VibeApp.java` (SpringBootApplication 및 컨트롤러 역할)
- **엔드포인트**: `GET /api/hello` -> "Hello, Vibe!" 반환

## 권장 추가 사항
- **개발 환경 개선**: Spring Boot DevTools 추가 필요
- **코드 효율성**: Lombok 도입 검토 필요

## 마지막 업데이트
- 일시: 2026-01-27
- 내용: 빌드 스크립트 검토 및 프로젝트 상태 기록 정비
