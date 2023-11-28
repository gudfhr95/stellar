import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("plugin.spring")

  id("com.google.cloud.tools.jib")
}

group = "app.stellar"
version = "0.0.0"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

jib {
  from {
    image = "eclipse-temurin:21-jre-alpine"
  }

  to {
    image = "backend"
  }
}

tasks.named<BootJar>("bootJar") {
  enabled = true
}
