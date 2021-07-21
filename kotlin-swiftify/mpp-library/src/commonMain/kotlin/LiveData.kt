package dev.icerock.moko.mvvm

expect open class LiveData<T> constructor(init: T) {
    open val value: T

    fun addObserver(observer: (T) -> Unit)
}

fun LiveData<String>.stringLd(): String = this.value

fun LiveData<Int>.intLd(): Int = this.value

class TestViewModel {
    val loginLiveData: LiveData<String> = LiveData<String>("test")
    val counterLiveData: LiveData<Int> = LiveData<Int>(0)
}
