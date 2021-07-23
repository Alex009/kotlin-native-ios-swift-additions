/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.kswift.plugin

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import java.io.File

open class KSwiftPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.withType(KotlinMultiplatformPluginWrapper::class.java) { pluginWrapper ->
            val multiplatformExtension = target.extensions
                .getByType(pluginWrapper.projectExtensionClass.java)

            applyToKotlinMultiplatform(multiplatformExtension)
        }
    }

    private fun applyToKotlinMultiplatform(extension: KotlinMultiplatformExtension) {
        extension.targets
            .withType<KotlinNativeTarget>()
            .matching { it.konanTarget.family.isAppleFamily }
            .configureEach { applyToAppleTarget(it) }
    }

    private fun applyToAppleTarget(target: KotlinNativeTarget) {
        target.binaries
            .withType<Framework>()
            .configureEach { applyToAppleFramework(it) }
    }

    private fun applyToAppleFramework(framework: Framework) {
        val linkTask: KotlinNativeLink = framework.linkTask
        linkTask.doLast(PostProcessLinkTask(framework = framework))
    }

    private class PostProcessLinkTask(
        private val framework: Framework
    ) : Action<Task> {

        override fun execute(task: Task) {
            val linkTask: KotlinNativeLink = task as KotlinNativeLink

            linkTask.libraries
                .plus(linkTask.intermediateLibrary.get())
                .forEach { library ->
                    KLibProcessor(
                        kotlinFrameworkName = framework.baseName,
                        outputDir = File(framework.outputDirectory.parent, "AdditionsSources"),
                        library = library
                    ).process()
                }
        }
    }
}
