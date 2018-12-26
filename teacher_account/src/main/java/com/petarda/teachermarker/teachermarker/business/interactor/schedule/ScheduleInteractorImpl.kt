package com.petarda.teachermarker.teachermarker.business.interactor.schedule

import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.petarda.teachermarker.teachermarker.api.JournalApi
import com.petarda.teachermarker.teachermarker.business.model.Schedule
import com.petarda.teachermarker.teachermarker.business.model.Subject
import com.petarda.teachermarker.teachermarker.business.toRoomSubject
import com.petarda.teachermarker.teachermarker.business.toScheduleList
import com.petarda.teachermarker.teachermarker.room.dao.RoomSubjectDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ScheduleInteractorImpl : ScheduleInteractor {
    @Inject
    lateinit var journalApi: JournalApi

    @Inject
    lateinit var roomSubjectDao: RoomSubjectDao

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    override fun getSchedulesBySearchString(searchString: String): Single<List<Schedule>> =
        journalApi.getStudentsJournal(searchString)
            .map { it.toScheduleList() }

    override fun saveSubject(subject: Subject): Completable = Completable.fromAction {
        roomSubjectDao.insertSubject(subject.toRoomSubject())
    }
}