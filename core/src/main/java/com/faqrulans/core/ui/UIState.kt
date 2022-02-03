package com.faqrulans.core.ui

sealed class UIState<T>(val data: T? = null, val message: Int? = null) {
    class Success<T>(data: T) : UIState<T>(data)
    class Loading<T>(data: T? = null) : UIState<T>(data)
    class Error<T>(message: Int, data: T? = null) : UIState<T>(data, message)
}
