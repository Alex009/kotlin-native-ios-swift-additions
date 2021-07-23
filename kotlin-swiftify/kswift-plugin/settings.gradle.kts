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
        kotlin("jvm") version("1.5.20")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

rootProject.name = "kswift-plugin"
