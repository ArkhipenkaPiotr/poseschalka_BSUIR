package com.petarda.teachermarker.teachermarker.screen.subjects

import android.arch.lifecycle.MutableLiveData
import com.cardpay.digipass.android.base.BaseViewModel
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.petarda.teachermarker.teachermarker.business.interactor.subjects.SubjectsInteractor
import com.petarda.teachermarker.teachermarker.business.model.Subject
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SubjectsViewModel : BaseViewModel() {

    @Inject
    lateinit var subjectsInteractor: SubjectsInteractor

    internal val subjects: MutableLiveData<List<Subject>> = MutableLiveData()

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
        observeSubjects()
    }

    private fun observeSubjects() =
        subjectsInteractor.getSubjects()
            .subscribeOn(Schedulers.io())
            .subscribe(subjects::postValue)
            .let(this::addDisposable)

    internal fun onSubjectsClicked(id: Long) {

    }
}