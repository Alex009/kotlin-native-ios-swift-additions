package dev.icerock.moko.mvvm

import cocoapods.TestFramework.SwiftAddition

actual open class LiveData<T> {
    actual open val value: T = TODO()

    private val additions = SwiftAddition()

    actual fun addObserver(observer: (T) -> Unit) {
        val result = additions.getValue()
        println("$result")
    }
}
