package com.vironit.fireant.business.interactor.authentication

import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationInteractor {
    fun signIn(login: String, password: String): Completable
    fun registrate(login: String, firstPassword: String, secondPassword: String): Completable
    fun logout(): Completable
}