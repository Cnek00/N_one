# Proje Klasör Yapısı ve Dosya İçerikleri

Bu belge, projenin klasör yapısını ve her önemli dosyanın içeriğini tek yerde görmek için hazırlanmıştır.

## 1) Proje dizin ağacı

```text
notebook/
├── .github/
│   └── modernize/
│       └── java-upgrade/
│           ├── 20260628233018/
│           │   └── logs/
│           │       └── 0.log
│           ├── 20260628233041/
│           │   ├── logs/
│           │   │   └── 0.log
│           │   ├── plan.md
│           │   └── progress.md
│           └── hooks/
│               └── scripts/
│                   ├── recordToolUse.ps1
│                   └── recordToolUse.sh
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── .vscode/
│   └── settings.json
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/N_ONE/notebook/
│   │   │       ├── NotebookApplication.java
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── HomeController.java
│   │   │       │   └── NoteController.java
│   │   │       ├── entity/
│   │   │       │   └── User.java
│   │   │       ├── model/
│   │   │       │   └── Note.java
│   │   │       ├── repository/
│   │   │       │   └── NoteRepository.java
│   │   │       └── service/
│   │   │           └── NoteService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/example/N_ONE/notebook/
│           └── NotebookApplicationTests.java
├── .gitattributes
├── .gitignore
├── HELP.md
├── docker-compose.yml
├── dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
└── target/   (build çıktıları)
```

## 2) Dosya içerikleri

### A) Kök dizin dosyaları

#### [pom.xml](pom.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example.N_ONE</groupId>
	<artifactId>notebook</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name/>
	<description/>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security-oauth2-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security-oauth2-client-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<executions>
					<execution>
						<id>default-compile</id>
						<phase>compile</phase>
						<goals>
							<goal>compile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
					<execution>
						<id>default-testCompile</id>
						<phase>test-compile</phase>
						<goals>
							<goal>testCompile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>
```

#### [docker-compose.yml](docker-compose.yml)
```yaml
version: '3.8'

services:
  # 1. PostgreSQL 18 Konteyneri
  postgres_db:
    image: postgres:18-alpine
    container_name: n_one_postgres
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: n_one_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: admin
    volumes:
      - postgres_data:/var/lib/postgresql/data

  # 2. Bizim Spring Boot Uygulaması (Java 21)
  app:
    build: .
    container_name: n_one_backend
    ports:
      - "8080:8080"
    environment:
      - DB_HOST=postgres_db
    depends_on:
      - postgres_db

volumes:
  postgres_data:
```

#### [dockerfile](dockerfile)
```dockerfile
# 1. Aşama: Uygulamayı derleme (Build) - Java 21 JDK
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Bağımlılıkları önceden indir
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# 2. Aşama: Çalıştırma ortamı - Java 21 JRE
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### [HELP.md](HELP.md)
```md
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.1.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/4.1.0/reference/web/spring-security.html)
* [OAuth2 Client](https://docs.spring.io/spring-boot/4.1.0/reference/web/spring-security.html#web.security.oauth2.client)
* [Validation](https://docs.spring.io/spring-boot/4.1.0/reference/io/validation.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
```

#### [.gitignore](.gitignore)
```text
HELP.md
target/
.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

### STS ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/
!**/src/main/**/build/
!**/src/test/**/build/

### VS Code ###
.vscode/
```

#### [.gitattributes](.gitattributes)
```text
/mvnw text eol=lf
*.cmd text eol=crlf
```

#### [.vscode/settings.json](.vscode/settings.json)
```json
{
    "java.configuration.updateBuildConfiguration": "interactive"
}
```

#### [.mvn/wrapper/maven-wrapper.properties](.mvn/wrapper/maven-wrapper.properties)
```properties
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.16/apache-maven-3.9.16-bin.zip
```

#### [mvnw](mvnw)
```sh
#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.4
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url base for downloading maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence the output
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set JAVACMD and JAVACCMD
set_java_home() {
  # For Cygwin and MinGW, ensure paths are in Unix format before anything is touched
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"

      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variable is not defined correctly, so mvnw cannot run." >&2
        echo "JAVA_HOME is set to \"$JAVA_HOME\", but \"\$JAVA_HOME/bin/java\" or \"\$JAVA_HOME/bin/javac\" does not exist." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$((
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v java
    ))" || :
    JAVACCMD="$((
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v javac
    ))" || :

    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "The java/javac command does not exist in PATH nor is JAVA_HOME set, so mvnw cannot run." >&2
      return 1
    fi
  fi
}
```

### B) Main kaynak kodu

#### [src/main/java/com/example/N_ONE/notebook/NotebookApplication.java](src/main/java/com/example/N_ONE/notebook/NotebookApplication.java)
```java
package com.example.N_ONE.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class NotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}

}
```

#### [src/main/java/com/example/N_ONE/notebook/config/SecurityConfig.java](src/main/java/com/example/N_ONE/notebook/config/SecurityConfig.java)
```java
package com.example.N_ONE.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. POST isteklerini engelleyen CSRF korumasını kapatıyoruz
            .csrf(csrf -> csrf.disable())
            // 2. Tüm urller için giriş zorunluluğunu kaldırıyoruz (Herkes gelebilir)
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        
        return http.build();
    }
}
```

#### [src/main/java/com/example/N_ONE/notebook/controller/HomeController.java](src/main/java/com/example/N_ONE/notebook/controller/HomeController.java)
```java
package com.example.N_ONE.notebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Giriş başarılı kanka! Notebook projesinin backend dünyasına hoş geldin. 🚀";
    }
}
```

#### [src/main/java/com/example/N_ONE/notebook/controller/NoteController.java](src/main/java/com/example/N_ONE/notebook/controller/NoteController.java)
```java
package com.example.N_ONE.notebook.controller;

import com.example.N_ONE.notebook.model.Note;
import com.example.N_ONE.notebook.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes") // Tüm istekler http://localhost:8080/api/notes ile başlayacak
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 1. Tüm Notları Listele (GET -> /api/notes)
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // 2. ID'ye Göre Not Getir (GET -> /api/notes/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Yeni Not Ekle (POST -> /api/notes)
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }

    // 4. Not Sil (DELETE -> /api/notes/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
```

#### [src/main/java/com/example/N_ONE/notebook/entity/User.java](src/main/java/com/example/N_ONE/notebook/entity/User.java)
```java
package com.example.N_ONE.notebook.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor // Önceki hatalı kelime düzeltildi kanka
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "google_sub_id", unique = true)
    private String googleSubId;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
```

#### [src/main/java/com/example/N_ONE/notebook/model/Note.java](src/main/java/com/example/N_ONE/notebook/model/Note.java)
```java
package com.example.N_ONE.notebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes") // Veritabanındaki tablo adı
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatik artan ID
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // Uzun not içerikleri için TEXT tipi
    private String content;

    private LocalDateTime createdAt;

    // Proje ayağa kalkarken otomatik tarih atanması için:
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getter ve Setter Metotları ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
```

#### [src/main/java/com/example/N_ONE/notebook/repository/NoteRepository.java](src/main/java/com/example/N_ONE/notebook/repository/NoteRepository.java)
```java
package com.example.N_ONE.notebook.repository;

import com.example.N_ONE.notebook.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // JpaRepository sayesinde; save(), findAll(), findById(), delete() gibi tüm metotlar otomatik hazır geliyor.
}
```

#### [src/main/java/com/example/N_ONE/notebook/service/NoteService.java](src/main/java/com/example/N_ONE/notebook/service/NoteService.java)
```java
package com.example.N_ONE.notebook.service;

import com.example.N_ONE.notebook.model.Note;
import com.example.N_ONE.notebook.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    // Bağımlılık enjeksiyonu (Dependency Injection)
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Tüm notları getir
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // ID'ye göre tek bir not getir
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // Yeni not kaydet veya mevcut notu güncelle
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    // Not sil
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
```

#### [src/main/resources/application.properties](src/main/resources/application.properties)
```properties
# Uygulama Adı ve Port Ayarı
spring.application.name=N_ONE
server.port=8080

spring.security.user.name=cenk
spring.security.user.password=123456
# PostgreSQL Bağlantı Ayarları

# Veritabanı Host bilgisini dışarıdan (Environment) alacak, bulamazsa localhost kullanacak
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:5432/n_one_db
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate Ayarları
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### C) Test dosyası

#### [src/test/java/com/example/N_ONE/notebook/NotebookApplicationTests.java](src/test/java/com/example/N_ONE/notebook/NotebookApplicationTests.java)
```java
package com.example.N_ONE.notebook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotebookApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## 3) Kısa özet

- Proje, Spring Boot tabanlı bir Java uygulamasıdır.
- Ana iş mantığı, controller → service → repository → model akışında yürür.
- Veritabanı modeli olarak Note ve User bulunur.
- Uygulama Docker ile çalıştırılabilir durumdadır.
- [src/main/java/com/example/N_ONE/notebook/controller/NoteController.java](src/main/java/com/example/N_ONE/notebook/controller/NoteController.java) not CRUD işlemlerini yönetir.
- [src/main/java/com/example/N_ONE/notebook/service/NoteService.java](src/main/java/com/example/N_ONE/notebook/service/NoteService.java) iş mantığını içerir.
- [src/main/java/com/example/N_ONE/notebook/repository/NoteRepository.java](src/main/java/com/example/N_ONE/notebook/repository/NoteRepository.java) veritabanı erişimini sağlar.
