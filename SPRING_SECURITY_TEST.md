# Spring Security 실습 테스트 가이드 (JWT 기반)

이 문서는 생성된 Spring Security 예제를 JWT (JSON Web Token) 기반 인증 방식으로 테스트하는 방법을 안내합니다.

## 1. 애플리케이션 실행

먼저, 아래 명령어를 사용하여 Spring Boot 애플리케이션을 실행합니다.

```bash
mvn spring-boot:run
```

애플리케이션이 실행되면, 새로운 터미널을 열고 아래의 테스트들을 진행할 수 있습니다.

---

## 2. JWT 기반 인증 및 테스트 방법

현재 시스템은 H2 인메모리 데이터베이스를 사용합니다. 애플리케이션이 시작될 때 `admin` 계정 (비밀번호: `password`)이 데이터베이스에 자동으로 생성됩니다.

### 1. 새로운 사용자 등록 (인증 불필요)

`POST /register` 엔드포인트에 `username`과 `password`를 전송하여 새 사용자를 등록할 수 있습니다. 이 엔드포인트는 인증이 필요 없습니다.

**명령어:**
```bash
curl -X POST -d "username=newuser&password=password" http://localhost:8080/register
```

### 2. JWT 토큰 발급 (로그인)

등록된 사용자 (또는 기본 `admin` 계정)로 로그인하여 JWT 토큰을 발급받습니다. 이 토큰은 이후 보호된 리소스에 접근할 때 사용됩니다.

**명령어:**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"username":"admin", "password":"password"}' http://localhost:8080/api/login
```
(응답으로 받은 `"jwt"` 값을 복사해두세요. 예: `"eyJhbGciOiJIUzI1NiJ9..."`)

### 3. 발급받은 토큰으로 보호된 리소스 접근

위에서 발급받은 JWT 토큰을 `Authorization: Bearer <토큰값>` 헤더에 포함하여 보호된 엔드포인트에 접근합니다.

**명령어 예시:**
```bash
TOKEN="여기에_위에서_받은_JWT_값을_붙여넣으세요" # 예: TOKEN="eyJhbGciOiJIUzI1NiJ9..."
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/user/dashboard
```

### 4. H2 데이터베이스 콘솔 접근

웹 브라우저에서 아래 주소로 이동하여 데이터베이스에 저장된 사용자 정보를 직접 확인할 수 있습니다.

-   **URL**: `http://localhost:8080/h2-console`

콘솔 접속 시 아래 정보를 사용하세요.
-   **JDBC URL**: `jdbc:h2:mem:testdb`
-   **User Name**: `sa`
-   **Password**: (비워두세요)

---

## 3. 엔드포인트 권한 테스트 (JWT 토큰 사용)

모든 보호된 엔드포인트는 유효한 JWT 토큰을 `Authorization: Bearer` 헤더에 포함해야 접근 가능합니다.

### 1. 공개 페이지 접근 (인증 없이)

누구나 접근 가능한 페이지입니다.

**명령어:**
```bash
curl http://localhost:8080/
```

### 2. USER 권한 페이지 접근 (`USER` 또는 `ADMIN` 권한의 JWT 토큰 사용)

`admin` 계정은 `USER`와 `ADMIN` 권한을 모두 가지고 있습니다. 로그인하여 발급받은 JWT 토큰을 사용합니다.

**명령어:**
```bash
TOKEN="여기에_위에서_받은_JWT_값을_붙여넣으세요" # admin 계정으로 발급받은 토큰
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/user/dashboard
```

### 3. ADMIN 권한 페이지 접근 (ADMIN 권한의 JWT 토큰 사용)

`admin` 계정으로 로그인하여 발급받은 JWT 토큰을 사용합니다.

**명령어:**
```bash
TOKEN="여기에_위에서_받은_JWT_값을_붙여넣으세요" # admin 계정으로 발급받은 토큰
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/admin/panel
```

### 4. ADMIN 권한 페이지 접근 (ADMIN 권한 없는 JWT 토큰 사용)

`newuser` 계정은 `ADMIN` 권한이 없으므로 접근이 거부됩니다 (`403 Forbidden`). `-v` 옵션을 통해 상세한 HTTP 응답을 확인할 수 있습니다.

**명령어:**
```bash
# 먼저 newuser로 로그인하여 토큰을 발급받아야 합니다.
# curl -X POST -H "Content-Type: application/json" -d '{"username":"newuser", "password":"password"}' http://localhost:8080/api/login
TOKEN="여기에_newuser로_발급받은_JWT_값을_붙여넣으세요"
curl -v -H "Authorization: Bearer $TOKEN" http://localhost:8080/admin/panel
```

### 5. 메서드 보안 페이지 접근 (ADMIN 권한의 JWT 토큰 사용)

`@PreAuthorize("hasRole('ADMIN')")`으로 보호된 메서드에 `admin` 계정으로 로그인하여 발급받은 JWT 토큰을 사용합니다.

**명령어:**
```bash
TOKEN="여기에_admin으로_발급받은_JWT_값을_붙여넣으세요"
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/secure/method
```

이제 위 명령어들을 통해 직접 JWT 기반 Spring Security의 경로별, 메서드별 권한 설정을 테스트해보실 수 있습니다.
