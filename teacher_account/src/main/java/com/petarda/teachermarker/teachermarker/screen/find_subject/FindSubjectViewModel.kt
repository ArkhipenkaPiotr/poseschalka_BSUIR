package com.petarda.teachermarker.teachermarker.screen.find_subject

import android.arch.lifecycle.MutableLiveData
import com.cardpay.digipass.android.base.BaseViewModel
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.petarda.teachermarker.teachermarker.business.model.Schedule
import com.petarda.teachermarker.teachermarker.business.interactor.schedule.ScheduleInteractor
import com.petarda.teachermarker.teachermarker.business.toSubject
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class FindSubjectViewModel : BaseViewModel() {

    @Inject
    lateinit var scheduleInteractor: ScheduleInteractor

    lateinit var currentClickedSchedule: UUID

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    internal val schedules: MutableLiveData<List<Schedule>> = MutableLiveData()
    internal val uiEvents: MutableLiveData<UIEvents> = MutableLiveData()

    fun onGetSubjectsButtonClicked(searchString: String) =
        scheduleInteractor.getSchedulesBySearchString(searchString)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                uiEvents.postValue(UIEvents.SHOW_PROGRESS)
            }
            .doFinally {
                uiEvents.postValue(UIEvents.HIDE_PROGRESS)
            }
            .subscribe(schedules::postValue) {
                Timber.e("$it")
            }
            .let(this::addDisposable)

    fun onSubjectClicked(id: UUID) {
        currentClickedSchedule = id
        uiEvents.postValue(UIEvents.SHOW_LESSONS_AMOUNT_PICKER)
    }

    fun onLessonsAmountPicked(amount: Int) =
        scheduleInteractor.saveSubject(schedules.value?.find { it.id == currentClickedSchedule }?.toSubject(amount)!!)
            .subscribeOn(Schedulers.io())
            .subscribe {
                uiEvents.postValue(UIEvents.CLOSE_ACTIVITY)
            }

    internal enum class UIEvents {
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        HIDE_KEYBOARD,
        SHOW_LESSONS_AMOUNT_PICKER,
        CLOSE_ACTIVITY
    }
}