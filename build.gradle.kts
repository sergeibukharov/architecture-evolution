import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.BUILD-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.3.50"
}

allprojects {
	group = "com.thoughtworks"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
		jcenter()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

}

java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}



dependencies {
	// spring modules
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")

	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// tools
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// dev tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// view
	implementation( "org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")

	//persistance
	runtimeOnly("com.h2database:h2")
	implementation("javax.persistence:javax.persistence-api:2.2")

	// tests
	testCompile("org.junit.jupiter:junit-jupiter-api:5.5.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testCompile("io.mockk:mockk:1.9.3")
}

tasks.test {
	useJUnitPlatform()
}