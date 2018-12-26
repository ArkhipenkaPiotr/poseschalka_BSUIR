package com.petarda.teachermarker.teachermarker.business.interactor.schedule

import com.petarda.teachermarker.teachermarker.business.model.Schedule
import com.petarda.teachermarker.teachermarker.business.model.Subject
import io.reactivex.Completable
import io.reactivex.Single

interface ScheduleInteractor {
    fun getSchedulesBySearchString(searchString: String): Single<List<Schedule>>
    fun saveSubject(subject: Subject): Completable
}