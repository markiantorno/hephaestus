import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30-M1"
}

group = "org.hl7.fhir"
version = "1.0.0-SNAPSHOT"

repositories {
    google()
    jcenter()
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
    maven {
        url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
    }
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.8.0")
    implementation("org.apache.poi:poi-ooxml:5.0.0")

    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")
    implementation("ca.miantorno:susu:1.0.0-a1-SNAPSHOT")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}