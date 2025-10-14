package com.gauravbajaj.interviewready.ui.base


sealed class UIState<out T> {
    object Initial : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()


    fun <T> success(data: T) = Success(data)
    fun error(message: String) = Error(message)
}
