# Spring Security 실습 테스트 가이드

이 문서는 생성된 Spring Security 예제를 테스트하는 방법을 안내합니다.

## 1. 애플리케이션 실행

먼저, 아래 명령어를 사용하여 Spring Boot 애플리케이션을 실행합니다.

```bash
mvn spring-boot:run
```

애플리케이션이 실행되면, 새로운 터미널을 열고 아래의 테스트들을 진행할 수 있습니다.

---

## 2. DB 연동 및 테스트 방법

현재 시스템은 H2 인메모리 데이터베이스를 사용합니다. 애플리케이션이 시작될 때 `admin` 계정 (비밀번호: `password`)이 데이터베이스에 자동으로 생성됩니다.

### 1. 새로운 사용자 등록

`POST /register` 엔드포인트에 `username`과 `password`를 전송하여 새 사용자를 등록할 수 있습니다.

**명령어:**
```bash
curl -X POST -d "username=newuser&password=password" http://localhost:8080/register
```

### 2. 등록한 사용자로 로그인

등록한 `newuser` 계정으로 `USER` 권한이 필요한 페이지에 접근합니다.

**명령어:**
```bash
curl -u newuser:password http://localhost:8080/user/dashboard
```

### 3. H2 데이터베이스 콘솔 접근

웹 브라우저에서 아래 주소로 이동하여 데이터베이스에 저장된 사용자 정보를 직접 확인할 수 있습니다.

- **URL**: `http://localhost:8080/h2-console`

콘솔 접속 시 아래 정보를 사용하세요.
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User Name**: `sa`
- **Password**: (비워두세요)

---

## 3. 엔드포인트 권한 테스트

### 1. 공개 페이지 접근 (인증 없이)

누구나 접근 가능한 페이지입니다.

**명령어:**
```bash
curl http://localhost:8080/
```

### 2. USER 권한 페이지 접근 (`admin` 계정)

`admin` 계정은 `USER`와 `ADMIN` 권한을 모두 가지고 있습니다.

**명령어:**
```bash
curl -u admin:password http://localhost:8080/user/dashboard
```

### 3. ADMIN 권한 페이지 접근 (`newuser` 계정)

`newuser` 계정은 `ADMIN` 권한이 없으므로 접근이 거부됩니다 (`403 Forbidden`). `-v` 옵션을 통해 상세한 HTTP 응답을 확인할 수 있습니다.

**명령어:**
```bash
curl -u newuser:password -v http://localhost:8080/admin/panel
```

### 4. ADMIN 권한 페이지 접근 (`admin` 계정)

`admin` 계정으로 `/admin/panel`에 접근합니다.

**명령어:**
```bash
curl -u admin:password http://localhost:8080/admin/panel
```

### 5. 메서드 보안 페이지 접근 (`admin` 계정)

`@PreAuthorize("hasRole('ADMIN')")`으로 보호된 메서드에 `admin` 계정으로 접근합니다.

**명령어:**
```bash
curl -u admin:password http://localhost:8080/secure/method
```

이제 위 명령어들을 통해 직접 Spring Security의 경로별, 메서드별 권한 설정을 테스트해보실 수 있습니다.
