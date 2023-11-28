rootProject.name = "stellar"

pluginManagement {
  val kotlinVersion: String by settings
  val ktlintGradleVersion: String by settings
  val detektVersion: String by settings
  val springBootVersion: String by settings
  val springDependencyManagementVersion: String by settings

  plugins {
    kotlin("jvm") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version ktlintGradleVersion
    id("io.gitlab.arturbosch.detekt") version detektVersion

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
  }
}
