import org.apache.tools.ant.taskdefs.Jar
import org.gradle.api.file.CopySpec
import org.gradle.kotlin.dsl.compile
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven { setUrl("https://repo.spring.io/milestone") }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.0.M3")
    }
}

repositories {
    mavenCentral()
    maven { setUrl("https://repo.spring.io/milestone") }
    maven { setUrl("https://repo.spring.io/snapshot") }
}

plugins {
    kotlin("jvm")
    id("io.spring.dependency-management") version "1.0.3.RELEASE"
    id("org.jetbrains.kotlin.plugin.spring") version embeddedKotlinVersion
}

apply {
    plugin("org.springframework.boot")
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter")
    compile(kotlin("stdlib-jre8"))
    compile(kotlin("reflect"))
}
val project = mapOf(name to "backend")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}