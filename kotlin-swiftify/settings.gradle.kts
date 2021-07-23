/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    plugins {
        kotlin("multiplatform") version("1.5.20")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

rootProject.name = "moko-swiftify"

includeBuild("kswift-plugin")

include(":mpp-library")
