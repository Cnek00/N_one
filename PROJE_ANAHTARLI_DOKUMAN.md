# Proje Anahtarlı Döküman

Bu dosya, projenin klasör yapısını, dosyaları ve her dosyanın içeriğini ayrı bir doküman olarak sunar.

## Klasör yapısı

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
└── target/
```

## Dosya içerikleri

### [pom.xml](pom.xml)
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
	</dependencies>
</project>
```

### [docker-compose.yml](docker-compose.yml)
```yaml
version: '3.8'

services:
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

### [src/main/java/com/example/N_ONE/notebook/NotebookApplication.java](src/main/java/com/example/N_ONE/notebook/NotebookApplication.java)
```java
package com.example.N_ONE.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication.class, args);
    }
}
```

### [src/main/java/com/example/N_ONE/notebook/controller/HomeController.java](src/main/java/com/example/N_ONE/notebook/controller/HomeController.java)
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

### [src/main/java/com/example/N_ONE/notebook/controller/NoteController.java](src/main/java/com/example/N_ONE/notebook/controller/NoteController.java)
```java
package com.example.N_ONE.notebook.controller;

import com.example.N_ONE.notebook.model.Note;
import com.example.N_ONE.notebook.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
```

### [src/main/java/com/example/N_ONE/notebook/entity/User.java](src/main/java/com/example/N_ONE/notebook/entity/User.java)
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
@NoArgsConstructor
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

### [src/main/java/com/example/N_ONE/notebook/model/Note.java](src/main/java/com/example/N_ONE/notebook/model/Note.java)
```java
package com.example.N_ONE.notebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

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

### [src/main/java/com/example/N_ONE/notebook/repository/NoteRepository.java](src/main/java/com/example/N_ONE/notebook/repository/NoteRepository.java)
```java
package com.example.N_ONE.notebook.repository;

import com.example.N_ONE.notebook.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
```

### [src/main/java/com/example/N_ONE/notebook/service/NoteService.java](src/main/java/com/example/N_ONE/notebook/service/NoteService.java)
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

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
```

### [src/main/resources/application.properties](src/main/resources/application.properties)
```properties
spring.application.name=N_ONE
server.port=8080
spring.security.user.name=cenk
spring.security.user.password=123456
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:5432/n_one_db
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### [src/test/java/com/example/N_ONE/notebook/NotebookApplicationTests.java](src/test/java/com/example/N_ONE/notebook/NotebookApplicationTests.java)
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

## Not
Bu dosya artık proje yapısını ve içeriklerini ayrı bir yerde toplu olarak gösterir.
