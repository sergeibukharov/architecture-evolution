plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation(project(":businessPeople"))
    implementation(project(":useCasePeople"))

    // spring modules
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.2.1.BUILD-SNAPSHOT")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.2.1.BUILD-SNAPSHOT")

    // tools
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.0")

    // view
    implementation( "org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // tests
    testCompile("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.2.1.BUILD-SNAPSHOT") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.3.0.RELEASE")
}