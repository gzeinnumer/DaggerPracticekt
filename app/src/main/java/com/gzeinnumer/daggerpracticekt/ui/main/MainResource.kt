package com.gzeinnumer.daggerpracticekt.ui.main

class MainResource<T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): MainResource<T> {
            return MainResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): MainResource<T> {
            return MainResource(Status.ERROR, null, msg)
        }

        fun <T> loading(): MainResource<T> {
            return MainResource(Status.LOADING, null, null)
        }
    }

}