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

**명령어 (모든 환경):**
```bash
curl -X POST -d "username=newuser&password=password" http://localhost:8080/register
```

### 2. JWT 토큰 발급 (로그인)

등록된 사용자 (또는 기본 `admin` 계정)로 로그인하여 JWT 토큰을 발급받습니다. 이 토큰은 이후 보호된 리소스에 접근할 때 사용됩니다.

**명령어 (Linux/macOS 환경):**
```bash
TOKEN=$(curl -s -X POST -H "Content-Type: application/json" -d '{"username":"admin", "password":"password"}' http://localhost:8080/api/login | grep -o '"jwt":"[^"]*"' | cut -d'"' -f4)
echo "발급된 토큰: $TOKEN"
```

**명령어 (Windows Command Prompt (cmd.exe) 환경):**
```cmd
@echo off
for /f "tokens=2 delims=:" %%a in ('curl -s -X POST -H "Content-Type: application/json" -d "{\"username\":\"admin\", \"password\":\"password\"}" http://localhost:8080/api/login ^| findstr /c:"jwt"') do (
    set "TOKEN=%%a"
)
set "TOKEN=%TOKEN:~2,-2%"
echo 발급된 토큰: %TOKEN%
```

**명령어 (Windows PowerShell 환경):**
```powershell
$response = Invoke-RestMethod -Uri "http://localhost:8080/api/login" -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"username":"admin", "password":"password"}'
$TOKEN = $response.jwt
Write-Host "발급된 토큰: $TOKEN"
```
(응답으로 받은 `"jwt"` 값을 복사해두세요. 예: `"eyJhbGciOiJIUzI1NiJ9..."`)

---

### 3. 발급받은 토큰으로 보호된 리소스 접근

위에서 발급받은 JWT 토큰을 `Authorization: Bearer <토큰값>` 헤더에 포함하여 보호된 엔드포인트에 접근합니다.

**명령어 (Linux/macOS 환경):**
```bash
# 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다.
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/user/dashboard
```

**명령어 (Windows Command Prompt (cmd.exe) 환경):**
```cmd
:: 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다.
curl -H "Authorization: Bearer %TOKEN%" http://localhost:8080/user/dashboard
```

**명령어 (Windows PowerShell 환경):**
```powershell
# 먼저 위 2.2에서 $TOKEN 변수를 설정해야 합니다.
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

**명령어 (모든 환경):**
```bash
curl http://localhost:8080/
```

### 2. USER 권한 페이지 접근 (`USER` 또는 `ADMIN` 권한의 JWT 토큰 사용)

`admin` 계정은 `USER`와 `ADMIN` 권한을 모두 가지고 있습니다. 로그인하여 발급받은 JWT 토큰을 사용합니다.

**명령어 (Linux/macOS 환경):**
```bash
# 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/user/dashboard
```

**명령어 (Windows Command Prompt (cmd.exe) 환경):**
```cmd
:: 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer %TOKEN%" http://localhost:8080/user/dashboard
```

**명령어 (Windows PowerShell 환경):**
```powershell
# 먼저 위 2.2에서 $TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/user/dashboard
```

### 3. ADMIN 권한 페이지 접근 (ADMIN 권한의 JWT 토큰 사용)

`admin` 계정으로 로그인하여 발급받은 JWT 토큰을 사용합니다.

**명령어 (Linux/macOS 환경):**
```bash
# 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/admin/panel
```

**명령어 (Windows Command Prompt (cmd.exe) 환경):**
```cmd
:: 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer %TOKEN%" http://localhost:8080/admin/panel
```

**명령어 (Windows PowerShell 환경):**
```powershell
# 먼저 위 2.2에서 $TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/admin/panel
```

### 4. ADMIN 권한 페이지 접근 (ADMIN 권한 없는 JWT 토큰 사용)

`newuser` 계정은 `ADMIN` 권한이 없으므로 접근이 거부됩니다 (`403 Forbidden`). `-v` 옵션을 통해 상세한 HTTP 응답을 확인할 수 있습니다.

**명령어 (Linux/macOS 환경):**
```bash
# 먼저 newuser로 로그인하여 토큰을 발급받아야 합니다 (위 2.2 참조, username="newuser" 사용).
# TOKEN=$(curl -s -X POST -H "Content-Type: application/json" -d '{"username":"newuser", "password":"password"}' http://localhost:8080/api/login | grep -o '"jwt":"[^"]*"' | cut -d'"' -f4)
curl -v -H "Authorization: Bearer $TOKEN" http://localhost:8080/admin/panel
```

**명령어 (Windows Command Prompt (cmd.exe) 환경):**
```cmd
:: 먼저 newuser로 로그인하여 토큰을 발급받아야 합니다 (위 2.2 참조, username="newuser" 사용).
:: for /f "tokens=2 delims=:" %%a in ('curl -s -X POST -H "Content-Type: application/json" -d "{\"username\":\"newuser\", \"password\":\"password\"}" http://localhost:8080/api/login ^| findstr /c:"jwt"') do (set "TOKEN=%%a")
:: set "TOKEN=%TOKEN:~2,-2%"
curl -v -H "Authorization: Bearer %TOKEN%" http://localhost:8080/admin/panel
```

**명령어 (Windows PowerShell 환경):**
```powershell
# 먼저 newuser로 로그인하여 토큰을 발급받아야 합니다 (위 2.2 참조, username="newuser" 사용).
# $response = Invoke-RestMethod -Uri "http://localhost:8080/api/login" -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"username":"newuser", "password":"password"}'
# $TOKEN = $response.jwt
curl -v -H "Authorization: Bearer $TOKEN" http://localhost:8080/admin/panel
```

### 5. 메서드 보안 페이지 접근 (ADMIN 권한의 JWT 토큰 사용)

`@PreAuthorize("hasRole('ADMIN')")`으로 보호된 메서드에 `admin` 계정으로 로그인하여 발급받은 JWT 토큰을 사용합니다.

**명령어 (Linux/macOS 환경):**
```bash
# 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/secure/method
```

**명령어 (Windows Command Prompt (cmd.exe) 환경):**
```cmd
:: 먼저 위 2.2에서 TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer %TOKEN%" http://localhost:8080/secure/method
```

**명령어 (Windows PowerShell 환경):**
```powershell
# 먼저 위 2.2에서 $TOKEN 변수를 설정해야 합니다 (admin 계정 토큰).
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/secure/method
```

이제 위 명령어들을 통해 직접 JWT 기반 Spring Security의 경로별, 메서드별 권한 설정을 테스트해보실 수 있습니다.

---

## 4. Postman을 이용한 테스트 방법

[Postman](https://www.postman.com/)과 같은 GUI API 클라이언트를 사용하면 더 편리하게 테스트할 수 있습니다.

### 1. JWT 토큰 발급 (로그인)

1.  Postman에서 새 요청(Request)을 생성합니다.
2.  HTTP 메서드를 `POST`로 설정하고, URL에 `http://localhost:8080/api/login`을 입력합니다.
3.  **Headers** 탭으로 이동하여, `Key`에 `Content-Type`, `Value`에 `application/json`을 입력합니다.
4.  **Body** 탭으로 이동하여, `raw`를 선택하고 타입을 `JSON`으로 변경합니다.
5.  아래와 같이 로그인 정보를 입력합니다.
    ```json
    {
        "username": "admin",
        "password": "password"
    }
    ```
6.  **Send** 버튼을 누르면, 아래와 같은 응답을 받게 됩니다.
    ```json
    {
        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sInN1YiI6ImFkbWluIiwiaWF0IjoxNzA3MjMyNDc5LCJleHAiOjE3MDczMTg4Nzl9.abcdefg..."
    }
    ```
7.  응답으로 받은 `jwt` 값(큰따옴표 안의 긴 문자열)을 복사합니다.

### 2. 보호된 리소스 접근

1.  Postman에서 다시 새 요청을 생성합니다.
2.  HTTP 메서드를 `GET`으로 설정하고, URL에 `http://localhost:8080/admin/panel`과 같은 보호된 리소스 주소를 입력합니다.
3.  **Authorization** 탭으로 이동합니다.
4.  `Type`을 `Bearer Token`으로 선택합니다.
5.  오른쪽 `Token` 입력란에 위에서 복사한 **JWT 값**을 붙여넣습니다.
6.  **Send** 버튼을 누르면, 성공적으로 리소스의 내용(예: "Admin Panel")을 응답으로 받게 됩니다.
