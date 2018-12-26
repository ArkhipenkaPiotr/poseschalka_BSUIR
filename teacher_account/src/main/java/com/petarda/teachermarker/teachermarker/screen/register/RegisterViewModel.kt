package com.petarda.teachermarker.teachermarker.screen.register

import android.arch.lifecycle.MutableLiveData
import com.cardpay.digipass.android.base.BaseViewModel
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.vironit.fireant.business.interactor.authentication.AuthenticationInteractor
import com.vironit.fireant.business.interactor.authentication.exception.IncorrectPasswordException
import com.vironit.fireant.business.interactor.authentication.exception.PasswordsDoesntMatchException
import com.vironit.fireant.business.interactor.authentication.exception.SuchUserAlreadyExistException
import com.vironit.fireant.business.repository.local_user.preferences.exception.NoCurrentLocalUserException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RegisterViewModel : BaseViewModel() {

    @Inject
    lateinit var authInteractor: AuthenticationInteractor

    val uiEvents: PublishSubject<UIEvents> = PublishSubject.create()
    val login: MutableLiveData<CharSequence> = MutableLiveData()
    val firstPassword: MutableLiveData<CharSequence> = MutableLiveData()
    val secondPassword: MutableLiveData<CharSequence> = MutableLiveData()

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    fun registerUserAndGoToChatList() {
        if (login.value.isNullOrEmpty() || firstPassword.value.isNullOrEmpty() || secondPassword.value.isNullOrEmpty()) {
            uiEvents.onNext(UIEvents.REGISTER_INPUT_VALUE_ERROR)
        } else {
            authInteractor
                .registrate(login.value.toString(), firstPassword.value.toString(), secondPassword.value.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { uiEvents.onNext(UIEvents.HIDE_ERROR) }
                .subscribe({
                    uiEvents.onNext(UIEvents.MOVE_TO_NEXT_SCREEN)
                }, {
                    if (it is IncorrectPasswordException) {
                        uiEvents.onNext(UIEvents.INCORRECT_PASSWORD_ERROR)
                    } else if (it is PasswordsDoesntMatchException) {
                        uiEvents.onNext(UIEvents.PASSWORD_DOESNT_MATCH_ERROR)
                    } else if (it is NoCurrentLocalUserException) {
                        uiEvents.onNext(UIEvents.MOVE_TO_NEXT_SCREEN)
                    } else if (it is SuchUserAlreadyExistException) {
                        uiEvents.onNext(UIEvents.USER_EXIST)
                    }
                })
                .let(this::addDisposable)
        }
    }

    enum class UIEvents {
        MOVE_TO_NEXT_SCREEN,
        INCORRECT_PASSWORD_ERROR,
        PASSWORD_DOESNT_MATCH_ERROR,
        HIDE_ERROR,
        REGISTER_INPUT_VALUE_ERROR,
        USER_EXIST
    }
}