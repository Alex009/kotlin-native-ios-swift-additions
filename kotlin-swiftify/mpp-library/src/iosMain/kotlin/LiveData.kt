package dev.icerock.moko.mvvm

import platform.UIKit.UILabel

actual open class LiveData<T> actual constructor(init: T) {
    actual open val value: T = init

    actual fun addObserver(observer: (T) -> Unit) {
        observer(value)
    }
}

fun UILabel.bind(liveData: LiveData<String>) {
    liveData.addObserver { this.text = it }
}
