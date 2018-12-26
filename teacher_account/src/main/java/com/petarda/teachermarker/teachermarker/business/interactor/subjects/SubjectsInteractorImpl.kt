package com.petarda.teachermarker.teachermarker.business.interactor.subjects

import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.petarda.teachermarker.teachermarker.business.model.Subject
import com.petarda.teachermarker.teachermarker.business.toSubject
import com.petarda.teachermarker.teachermarker.room.dao.RoomSubjectDao
import io.reactivex.Flowable
import javax.inject.Inject

class SubjectsInteractorImpl : SubjectsInteractor {
    @Inject
    lateinit var roomSubjectDao: RoomSubjectDao

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    override fun getSubjects(): Flowable<List<Subject>> =
        roomSubjectDao.getAllSubjects()
            .map { roomSubjects -> roomSubjects.map { it.toSubject() } }
}