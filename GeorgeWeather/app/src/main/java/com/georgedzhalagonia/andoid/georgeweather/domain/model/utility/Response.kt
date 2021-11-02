package com.georgedzhalagonia.andoid.georgeweather.domain.model.utility

sealed class Response<T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val e: Exception, val msg: String) : Response<T>()


}

fun <T> Response.Success<T>.successOperation(): Operation.Success<T> {
    return Operation.Success(this.data)
}

fun <T> Response.Error<T>.errorOperation(): Operation.Error<T> {
    return Operation.Error(this.e, this.msg)
}



