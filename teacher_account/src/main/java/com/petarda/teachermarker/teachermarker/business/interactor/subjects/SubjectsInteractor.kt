package com.petarda.teachermarker.teachermarker.business.interactor.subjects

import com.petarda.teachermarker.teachermarker.business.model.Subject
import io.reactivex.Flowable

interface SubjectsInteractor {
    fun getSubjects(): Flowable<List<Subject>>
}