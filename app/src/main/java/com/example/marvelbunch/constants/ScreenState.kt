package com.example.marvelbunch.constants

// if we want to make a class generic use <T>
// A generic class can be used to represent various types (data types)

sealed class ScreenState<T>(val data: T? = null, val message: String? = null) {

    // creating three subclasses to represent three different loading states

    class Success<T>(data: T) : ScreenState<T>(data)

    class Loading<T>(data: T? = null): ScreenState<T>(data)

    class Error<T>(message: String, data: T? = null) : ScreenState<T>(data, message)


}
