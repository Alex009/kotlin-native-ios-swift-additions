/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    kotlin("jvm")
    `maven-publish`
    `java-gradle-plugin`
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation("com.squareup:kotlinpoet:1.6.0")
    implementation(files("libs/kotlinx-metadata-klib-1.5.20.jar"))
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

gradlePlugin {
    plugins {
        create("swiftify") {
            id = "dev.icerock.moko.swiftify"
            implementationClass = "dev.icerock.moko.swiftify.plugin.SwiftifyPlugin"
        }
    }
}

publishing.publications.register("mavenJava", MavenPublication::class) {
    from(components["java"])
}
