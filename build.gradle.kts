import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  kotlin("jvm")
  id("org.jlleitschuh.gradle.ktlint")
  id("io.gitlab.arturbosch.detekt")
  id("jacoco")

  id("org.springframework.boot") apply false
  id("io.spring.dependency-management")
  kotlin("plugin.spring") apply false
  kotlin("plugin.jpa") apply false
}

group = "app.stellar"
version = "0.0.0"

allprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jlleitschuh.gradle.ktlint")
  apply(plugin = "io.gitlab.arturbosch.detekt")

  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "kotlin-spring")

  java.sourceCompatibility = JavaVersion.VERSION_21

  repositories {
    mavenCentral()
  }

  ktlint {
    filter {
      exclude { entry ->
        entry.file.toString().contains("generated")
      }
    }
  }

  detekt {
    config.setFrom(files("$rootDir/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "21"
    }
  }

  tasks.named<BootJar>("bootJar") {
    enabled = false
  }

  tasks.named<Jar>("jar") {
    enabled = false
  }
}

subprojects {
  val junitVersion: String by project
  val mockkVersion: String by project

  apply(plugin = "jacoco")

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:$junitVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
  }

  tasks.named<BootJar>("bootJar") {
    enabled = true
  }

  tasks.withType<Test> {
    useJUnitPlatform()

    finalizedBy("jacocoTestReport")
  }

  tasks.jacocoTestReport {
    reports {
      html.required.set(true)
      xml.required.set(false)
      csv.required.set(false)
    }

    finalizedBy("jacocoTestCoverageVerification")
  }

  tasks.jacocoTestCoverageVerification {
    violationRules {
      rule {
        element = "CLASS"

        limit {
          counter = "BRANCH"
          value = "COVEREDRATIO"
          minimum = "0.70".toBigDecimal()
        }

        limit {
          counter = "LINE"
          value = "COVEREDRATIO"
          minimum = "0.70".toBigDecimal()
        }

        excludes = listOf()

        isFailOnViolation = true
      }
    }
  }
}
