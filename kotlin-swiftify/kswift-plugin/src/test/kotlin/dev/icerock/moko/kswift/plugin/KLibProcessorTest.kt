/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.kswift.plugin

import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test

class KLibProcessorTest {
    private val klibFile = File("input.klib")
    private val outputDir = File("output")

    @BeforeTest
    fun setup() {
        val klibStream = this.javaClass.getResourceAsStream("mpp-library.klib")
        klibStream?.use { input ->
            klibFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    @Test
    @Ignore
    fun `test extensions processing`() {
        val processor = KLibProcessor(
            kotlinFrameworkName = "library",
            outputDir = outputDir,
            library = klibFile
        )

        processor.process()
    }
}