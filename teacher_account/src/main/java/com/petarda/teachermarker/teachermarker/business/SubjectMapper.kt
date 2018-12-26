package com.petarda.teachermarker.teachermarker.business

import com.petarda.teachermarker.teachermarker.business.model.Subject
import com.petarda.teachermarker.teachermarker.room.model.RoomSubject

fun RoomSubject.toSubject(): Subject =
        Subject(
            this.id,
            this.lessonType,
            this.subjectName,
            this.employees,
            this.lessonsAmount
        )

fun Subject.toRoomSubject(): RoomSubject =
        RoomSubject(
            lessonType = this.lessonType,
            subjectName = this.subjectName,
            employees = this.employees,
            lessonsAmount = this.lessonsAmount
        )