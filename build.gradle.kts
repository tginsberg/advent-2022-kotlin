/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

repositories {
   mavenCentral()
}

dependencies {
    testApi("org.junit.jupiter:junit-jupiter-engine:5.9.1") {
        because("Gradle needs this to run tests")
    }
    testImplementation("org.assertj:assertj-core:3.23.1") {
        because("I prefer AssertJ's fluid assertions over JUnit or Hamcrest")
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    test {
        useJUnitPlatform()
    }
}
