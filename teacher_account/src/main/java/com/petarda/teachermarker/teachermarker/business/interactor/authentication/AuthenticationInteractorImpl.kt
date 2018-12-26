package com.petarda.teachermarker.teachermarker.business.interactor.authentication

import android.content.Context
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.petarda.teachermarker.teachermarker.business.interactor.user_preference.preferences.LocalUserSharedPreferencesRepository
import com.petarda.teachermarker.teachermarker.room.dao.RoomUserDao
import com.petarda.teachermarker.teachermarker.room.model.RoomUser
import com.vironit.fireant.business.interactor.authentication.AuthenticationInteractor
import com.vironit.fireant.business.interactor.authentication.exception.IncorrectPasswordException
import com.vironit.fireant.business.interactor.authentication.exception.NoSuchUserException
import com.vironit.fireant.business.interactor.authentication.exception.PasswordsDoesntMatchException
import com.vironit.fireant.business.interactor.authentication.exception.SuchUserAlreadyExistException
import io.reactivex.Completable
import javax.inject.Inject

class AuthenticationInteractorImpl : AuthenticationInteractor {
    @Inject
    lateinit var roomLocalUserDao: RoomUserDao

    @Inject
    lateinit var localUserSharedPreferencesRepository: LocalUserSharedPreferencesRepository

    @Inject
    lateinit var context: Context

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    override fun signIn(login: String, password: String): Completable = Completable.fromAction {
        val user = roomLocalUserDao.getUserByUserName(userName = login) ?: throw NoSuchUserException()
        if (user.password != password) {
            throw IncorrectPasswordException()
        }
        localUserSharedPreferencesRepository.setCurrentUserId(user.id!!)
    }

    override fun logout() = Completable.fromAction {
        localUserSharedPreferencesRepository.resetCurrentUserId()
    }

    override fun registrate(login: String, firstPassword: String, secondPassword: String): Completable =
        Completable.fromAction {
            if (firstPassword != secondPassword) {
                throw PasswordsDoesntMatchException()
            }
            val id = roomLocalUserDao.insert(
                RoomUser(
                    userName = login,
                    password = firstPassword
                )
            )
            localUserSharedPreferencesRepository.setCurrentUserId(id)
        }.onErrorResumeNext {
            if (it is PasswordsDoesntMatchException) {
                Completable.error(it)
            } else {
                Completable.error(SuchUserAlreadyExistException())
            }
        }
}