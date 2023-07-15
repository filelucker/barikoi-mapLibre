package com.example.maplibrebarikoi.network

sealed class ResponseData<out T> {
    class Loading<T> : ResponseData<T>()
    data class Success<out T>(val data: T) : ResponseData<T>()
    data class Failed<out T>(val data: T) : ResponseData<T>()
    companion object {
        //|Returns Loading Instance|-----------------------------|
        fun <T> loading() = Loading<T>()

        //|Returns Success Instance|-----------------------------|
        fun <T> success(data: T) = Success<T>(data)

        //|Returns Failed Instance|------------------------------|
        fun <T> failed(data: T) = Failed<T>(data)
    }
}
