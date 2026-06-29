# Notebook Backend

Bu proje, Spring Boot tabanlı bir notebook/backend uygulamasıdır. Not ekleme, listeleme, görüntüleme ve silme işlemlerini destekler.

## Özellikler

- Spring Boot tabanlı backend
- Spring Data JPA ile veritabanı erişimi
- PostgreSQL desteği
- Docker ve Docker Compose ile çalıştırma
- Not CRUD işlemleri
- Basit güvenlik yapılandırması

## Teknoloji Stack

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL
- Lombok
- Maven
- Docker / Docker Compose

## Proje Yapısı

```text
src/
  main/
    java/com/example/N_ONE/notebook/
      config/
      controller/
      entity/
      model/
      repository/
      service/
    resources/
      application.properties
  test/
```

## Kurulum

### 1) Gereksinimler

- Java 21
- Maven
- Docker (isteğe bağlı)
- PostgreSQL (isteğe bağlı)

### 2) Yerel Çalıştırma

```bash
./mvnw spring-boot:run
```

Uygulama varsayılan olarak şu adreste çalışır:

- http://localhost:8080

### 3) Docker ile Çalıştırma

```bash
docker compose up --build
```

## API Endpoints

### Ana Sayfa

- GET `/`

### Notlar

- GET `/api/notes`
- GET `/api/notes/{id}`
- POST `/api/notes`
- DELETE `/api/notes/{id}`

## Veritabanı Ayarları

Uygulama ayarları [src/main/resources/application.properties](src/main/resources/application.properties) dosyasında tanımlıdır.

Varsayılan yapı:

- Veritabanı: `n_one_db`
- Kullanıcı: `postgres`
- Şifre: `admin`
- Port: `5432`

## Testler

```bash
./mvnw test
```

## Not

Bu proje geliştirme aşamasındadır ve ileride kullanıcı kimlik doğrulama, JWT, frontend entegrasyonu ve daha fazla özellik eklenebilir.

