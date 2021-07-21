/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.swiftify.plugin

import kotlinx.metadata.internal.library.MetadataLibrary
import kotlinx.metadata.internal.library.ToolingSingleFileKlibResolveStrategy
import kotlinx.metadata.internal.library.resolveSingleFileKlib
import org.jetbrains.kotlin.konan.file.File as KFile
import kotlinx.metadata.klib.KlibModuleMetadata
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

open class SwiftifyPlugin : Plugin<Project> {
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
        linkTask.doLast(PostProcessLinkTask())
    }

    private class PostProcessLinkTask : Action<Task> {
        override fun execute(task: Task) {
            val linkTask: KotlinNativeLink = task as KotlinNativeLink

            linkTask.libraries.forEach { processLibrary(it) }
        }

        private fun processLibrary(library: File) {
            val metadata: KlibModuleMetadata =
                KotlinMetadataLibraryProvider.readLibraryMetadata(library)

            metadata.annotations.forEach { annotation ->
                println("annotation metadata $annotation")
            }
            metadata.fragments.forEach { fragment ->
                println("fragment metadata $fragment")
            }
        }
    }
}

class KotlinMetadataLibraryProvider(private val library: MetadataLibrary) :
    KlibModuleMetadata.MetadataLibraryProvider {
    override val moduleHeaderData: ByteArray
        get() = library.moduleHeaderData

    override fun packageMetadata(fqName: String, partName: String): ByteArray =
        library.packageMetadata(fqName, partName)

    override fun packageMetadataParts(fqName: String): Set<String> =
        library.packageMetadataParts(fqName)

    companion object {
        fun readLibraryMetadata(libraryPath: File): KlibModuleMetadata {
            check(libraryPath.exists()) { "Library does not exist: $libraryPath" }

            val library = resolveSingleFileKlib(
                KFile(libraryPath.absolutePath),
                strategy = ToolingSingleFileKlibResolveStrategy
            )
            return KlibModuleMetadata.read(KotlinMetadataLibraryProvider(library))
        }
    }
}
