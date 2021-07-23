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
    implementation("io.outfoxx:swiftpoet:1.1.0")
    implementation(files("libs/kotlinx-metadata-klib-1.5.20.jar"))
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.5.20")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")

    testImplementation(kotlin("test"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

gradlePlugin {
    plugins {
        create("kswift") {
            id = "dev.icerock.moko.kswift"
            implementationClass = "dev.icerock.moko.kswift.plugin.KSwiftPlugin"
        }
    }
}

publishing.publications.register("mavenJava", MavenPublication::class) {
    from(components["java"])
}
