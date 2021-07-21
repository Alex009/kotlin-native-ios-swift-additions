package dev.icerock.moko.mvvm

import cocoapods.TestFramework.SwiftAddition
import cocoapods.TestFramework.SwiftAdditionMeta
import platform.Foundation.NSArray

actual open class LiveData<T> {
    actual open val value: T = TODO()

    private val additions = SwiftAddition()

    actual fun addObserver(observer: (T) -> Unit) {
        val result = additions.getValue()
        println("$result")
    }
}

fun LiveData<String>.stringLd(): String = ""

fun LiveData<Int>.intLd() = 0

private val someLogic: Int = {
    SwiftAddition.setBindLiveData {
//        (if as LiveData<T>).addOb
    }
   0
}()
