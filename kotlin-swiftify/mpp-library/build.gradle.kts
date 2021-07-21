/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    kotlin("multiplatform")
    id("dev.icerock.moko.swiftify")
}

kotlin {
    iosX64("ios") {
        binaries {
            framework {
                baseName = "library"
            }
        }
    }
}
