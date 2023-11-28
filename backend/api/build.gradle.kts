import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("plugin.spring")

  id("com.google.cloud.tools.jib")
}

group = "app.stellar.api"
version = "0.0.0"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.named<BootJar>("bootJar") {
  enabled = true
}
