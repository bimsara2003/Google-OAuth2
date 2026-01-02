# Spring Boot Google OAuth2 Login Implementation

This project implements **Google OAuth2 Login** (Sign in with Google) using Spring Boot Security. It follows security best practices by using **Environment Variables** to hide sensitive credentials (Client ID & Secret).

---

## 📋 Prerequisites

* **Java**: 17 or higher
* **Build Tool**: Maven
* **IDE**: IntelliJ IDEA (recommended) or any Java IDE
* **Google Cloud Account**: To generate API credentials

---

## 🚀 Step 1: Google Cloud Console Setup

1. Go to the [Google Cloud Console](https://console.cloud.google.com/).
2. Create a **New Project**.
3. Go to **APIs & Services** > **OAuth consent screen**.
   * Select **External**.
   * Fill in App Name and Support Email.
   * Click "Save and Continue".
4. Go to **Credentials** > **Create Credentials** > **OAuth Client ID**.
   * Application Type: **Web Application**.
   * **Authorized Redirect URIs**: Add the following URL:
     ```text
     http://localhost:8080/login/oauth2/code/google
     ```
5. Click **Create**.
6. **Copy the Client ID and Client Secret.** (Do not share these or commit them to GitHub).

---

## 🛠 Step 2: Dependencies

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

---

## ⚙️ Step 3: Configuration (application.yml)

We use Environment Variables here. **We do not hardcode keys in this file.**

**File location**: `src/main/resources/application.yml`

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}        # Reads from Environment Variable
            client-secret: ${GOOGLE_CLIENT_SECRET} # Reads from Environment Variable
            scope:
              - email
              - profile
```

---

## 🔒 Step 4: Security Configuration (Java Code)

Create a class named `SecurityConfig.java` to handle the authentication flow.

```java
package com.example.demo.config; // Change to your package name

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/public").permitAll()  // URLs accessible without login
                .anyRequest().authenticated()                 // All other URLs require login
            )
            .oauth2Login(Customizer.withDefaults());          // Enable Google Login
        
        return http.build();
    }
}
```

---

## 🏃‍♂️ Step 5: How to Run (Securely)

Since `application.yml` uses placeholders, you must provide the keys when running the app.

### Option A: Using IntelliJ IDEA (Recommended)

1. Click on the **Run Configuration** (Top right dropdown next to the Play button).
2. Select **Edit Configurations**.
3. Find the **Environment variables** field.
4. Click the **file icon** (Browse) and add these two variables:
   * Name: `GOOGLE_CLIENT_ID` | Value: `your-copied-client-id`
   * Name: `GOOGLE_CLIENT_SECRET` | Value: `your-copied-client-secret`
5. Click **OK** and **Run**.

### Option B: Using Command Line (Terminal)

**Windows (CMD)**:
```cmd
set GOOGLE_CLIENT_ID=your-client-id-here
set GOOGLE_CLIENT_SECRET=your-client-secret-here
mvn spring-boot:run
```

**Windows (PowerShell)**:
```powershell
$env:GOOGLE_CLIENT_ID="your-client-id-here"
$env:GOOGLE_CLIENT_SECRET="your-client-secret-here"
mvn spring-boot:run
```

**macOS/Linux (Bash)**:
```bash
export GOOGLE_CLIENT_ID=your-client-id-here
export GOOGLE_CLIENT_SECRET=your-client-secret-here
mvn spring-boot:run
```

---

## 🧪 Step 6: Testing the Application

1. Start the application using one of the methods above.
2. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```
3. You will be redirected to Google's login page.
4. After successful login, you'll be redirected back to your application.

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── config/
│   │       │   └── SecurityConfig.java
│   │       └── DemoApplication.java
│   └── resources/
│       └── application.yml
└── test/
```

---

## ⚠️ Important Security Notes

### ✅ DO:
* ✅ Use environment variables for sensitive data
* ✅ Add `application-local.yml` to `.gitignore` if you create one
* ✅ Revoke and regenerate credentials if accidentally exposed
* ✅ Use different credentials for development and production

### ❌ DON'T:
* ❌ **NEVER** commit your real `client-id` or `client-secret` to GitHub
* ❌ **NEVER** hardcode credentials in `application.yml` or `application.properties`
* ❌ **NEVER** share your credentials publicly

### 🚨 If You Accidentally Committed Credentials:
1. Go to Google Cloud Console immediately
2. Delete the exposed OAuth credentials
3. Generate new credentials
4. Use tools like `git-secrets` or `gitleaks` to scan your repository
5. Consider the exposed credentials compromised permanently

---

## 🔧 Troubleshooting

### Issue: "redirect_uri_mismatch" error
**Solution**: Ensure the redirect URI in Google Cloud Console exactly matches:
```
http://localhost:8080/login/oauth2/code/google
```

### Issue: Environment variables not working
**Solution**: 
* Restart your IDE after setting environment variables
* Verify variables are set correctly using `echo $GOOGLE_CLIENT_ID` (macOS/Linux) or `echo %GOOGLE_CLIENT_ID%` (Windows)

### Issue: 401 Unauthorized
**Solution**: Check that your Google OAuth credentials are active and not expired in the Google Cloud Console.

---

## 📚 Additional Resources

* [Spring Security OAuth2 Documentation](https://spring.io/guides/tutorials/spring-boot-oauth2)
* [Google OAuth2 Documentation](https://developers.google.com/identity/protocols/oauth2)
* [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)


---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](../../issues).

---

## 👤 Author

[@bimsara2003](https://github.com/bimsara2003)

---

## ⭐ Show Your Support

Give a ⭐️ if this project helped you!

