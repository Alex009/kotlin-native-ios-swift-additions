/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.kswift.plugin

import kotlinx.metadata.klib.KlibModuleMetadata
import org.jetbrains.kotlin.library.KotlinLibrary
import org.jetbrains.kotlin.library.resolveSingleFileKlib
import java.io.File

class KotlinMetadataLibraryProvider(
    private val library: KotlinLibrary
) : KlibModuleMetadata.MetadataLibraryProvider {
    override val moduleHeaderData: ByteArray
        get() = library.moduleHeaderData

    override fun packageMetadata(fqName: String, partName: String): ByteArray =
        library.packageMetadata(fqName, partName)

    override fun packageMetadataParts(fqName: String): Set<String> =
        library.packageMetadataParts(fqName)

    companion object {
        fun readLibraryMetadata(libraryPath: File): KlibModuleMetadata {
            check(libraryPath.exists()) { "Library does not exist: $libraryPath" }

            val library =
                resolveSingleFileKlib(org.jetbrains.kotlin.konan.file.File(libraryPath.absolutePath))

            return KlibModuleMetadata.read(KotlinMetadataLibraryProvider(library))
        }
    }
}
