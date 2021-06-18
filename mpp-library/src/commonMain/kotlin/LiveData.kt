package dev.icerock.moko.mvvm

expect open class LiveData<T> {
    open val value: T

    fun addObserver(observer: (T) -> Unit)
}
