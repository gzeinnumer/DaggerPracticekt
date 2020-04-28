package com.gzeinnumer.daggerpracticekt.ui.auth

class AuthResource<T> private constructor(
    val status: AuthStatus,
    val data: T?,
    val message: String?
) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(data: T): AuthResource<T> {
            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String): AuthResource<T> {
            return AuthResource(AuthStatus.ERROR, null, msg)
        }

        fun <T> loading(): AuthResource<T> {
            return AuthResource(AuthStatus.LOADING, null, null)
        }

        fun <T> logout(): AuthResource<T?> {
            return AuthResource(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}