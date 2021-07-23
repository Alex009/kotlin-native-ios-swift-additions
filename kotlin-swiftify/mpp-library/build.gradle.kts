/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("dev.icerock.moko.kswift")
}

version = "0.1.0"

kotlin {
    iosX64("ios")

    cocoapods {
        summary = "KSwift plugin sample"
        homepage = "https://github.com/Alex009"
    }
}
