package com.petarda.teachermarker.teachermarker.screen.authentication_base

import android.arch.lifecycle.MutableLiveData
import com.cardpay.digipass.android.base.BaseViewModel
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication

class AuthenticationViewModel : BaseViewModel() {

    val uiEvents: MutableLiveData<UIEvents> = MutableLiveData()

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    enum class UIEvents {
        GO_TO_LOGIN,
        GO_TO_REGISTER,
    }
}