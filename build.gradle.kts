import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.dokka") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "org.example"

java{
    toolchain{
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.example.MainKtKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.MainKtKt"
    }
}


//jar {
//    manifest {
//        attributes["Main-Class"] = "com.example.MainKt"
//    }
//}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("org.mockito:mockito-core:5.3.0")
    testImplementation("org.mockito:mockito-inline:5.3.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName = "my-app-all.jar"
    manifest {
        attributes("Main-Class" to "MainKt") // Укажите ваш главный класс
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}