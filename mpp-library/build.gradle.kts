import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.5.10"
}

group = "dev.icerock.moko.mvvm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val frameworksDir =
    project.file("../swift-additions-library/dist").absolutePath
val swiftLibs =
    "/Applications/Xcode.app/Contents/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk/usr/lib/swift"

println("frameworksDir: $frameworksDir")
println("swiftLibs: $swiftLibs")

val swiftFrameworksDir = project.file("build/swiftFrameworks")

val copySwiftInterfaces = tasks.create("copySwiftInterfaces", Copy::class) {
    from(frameworksDir) {
        include("**/*.swiftinterface")
    }
    into(swiftFrameworksDir)

    filter { line ->
        if (line.contains("@_exported import")) "//$line"
        else line
    }

    includeEmptyDirs = false
}

tasks.matching { it is org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile }
    .configureEach { dependsOn(copySwiftInterfaces) }

kotlin {
    iosX64("ios") {
        binaries {
            framework {
                baseName = "library"
            }
        }
    }

    targets
        .matching { it is KotlinNativeTarget }
        .configureEach {
            this as KotlinNativeTarget

            compilations.getByName("main") {
                val swiftAdditions by cinterops.creating {
                    defFile(rootProject.file("cinterop/swiftAdditions.def"))

                    compilerOpts("-F$frameworksDir")
                    compilerOpts("-L$swiftLibs")
                }
            }

            this.binaries
                .matching { it is Framework }
                .configureEach {
                    val framework = this as Framework

                    framework.linkerOpts("-F$frameworksDir", "-L$swiftLibs")
                }
        }
}
