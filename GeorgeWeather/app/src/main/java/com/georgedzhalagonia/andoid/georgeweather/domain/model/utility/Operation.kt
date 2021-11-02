package com.georgedzhalagonia.andoid.georgeweather.domain.model.utility

sealed class Operation<T> {
    class Loading<T>: Operation<T>()
    class Pending<T>: Operation<T>()
    data class Success<T>(val data: T): Operation<T>()
    data class Error<T>(val e: Exception, val msg: String): Operation<T>()
}