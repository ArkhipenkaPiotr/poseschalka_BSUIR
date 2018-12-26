package com.petarda.teachermarker.teachermarker.screen.login

import android.arch.lifecycle.MutableLiveData
import com.cardpay.digipass.android.base.BaseViewModel
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.vironit.fireant.business.interactor.authentication.AuthenticationInteractor
import com.vironit.fireant.business.interactor.authentication.exception.IncorrectPasswordException
import com.vironit.fireant.business.interactor.authentication.exception.NoSuchUserException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var authInteractor: AuthenticationInteractor


    val login: MutableLiveData<CharSequence> = MutableLiveData()
    val password: MutableLiveData<CharSequence> = MutableLiveData()
    val uiEvents: PublishSubject<UIEvents> = PublishSubject.create()
    val userName: MutableLiveData<CharSequence> = MutableLiveData()

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
//        checkUserAndGoToNextActivity()
    }

    fun authUserAndGoToChatList() {
        if (login.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            uiEvents.onNext(UIEvents.INPUT_VALUE_IS_EMPTY)
        } else {
            authInteractor
                .signIn(login.value.toString(), password.value.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { uiEvents.onNext(UIEvents.HIDE_ERROR) }
                .subscribe({
                    uiEvents.onNext(UIEvents.MOVE_TO_NEXT_SCREEN)
                }, {
                    if (it is NoSuchUserException) {
                        uiEvents.onNext(UIEvents.NO_EXIST_USER)
                    } else if (it is IncorrectPasswordException) {
                        uiEvents.onNext(UIEvents.INCORRECT_PASSWORD)
                    }
                })
                .let(this::addDisposable)
        }
    }

//    private fun checkUserAndGoToNextActivity() =
//        userState
//            .getCurrentUser()
//            .subscribeOn(Schedulers.io())
//            .subscribe(this::goToNextFragmentWithName) {
//                if (it is NoCurrentLocalUserException) {
//                    goToNextFragmentWithoutName()
//                }
//            }
//            .let(this::addDisposable)

    private fun goToNextFragment() {
        uiEvents.onNext(UIEvents.GO_TO_LOGIN_WITH_NAME)
    }

    private fun goToNextFragmentWithoutName() =
        uiEvents.onNext(UIEvents.GO_TO_LOGIN_WITHOUT_NAME)

    enum class UIEvents {
        GO_TO_LOGIN_WITH_NAME,
        GO_TO_REGISTER,
        GO_TO_LOGIN_WITHOUT_NAME,

        INPUT_VALUE_IS_EMPTY,
        MOVE_TO_NEXT_SCREEN,
        HIDE_ERROR,
        NO_EXIST_USER,
        INCORRECT_PASSWORD
    }
}