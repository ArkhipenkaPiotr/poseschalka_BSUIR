package com.petarda.teachermarker.teachermarker.business

import com.petarda.teachermarker.teachermarker.api.model.Journal
import com.petarda.teachermarker.teachermarker.api.model.JournalSchedule
import com.petarda.teachermarker.teachermarker.business.model.Schedule
import com.petarda.teachermarker.teachermarker.business.model.Subject
import java.lang.StringBuilder
import java.util.*

fun Journal.toScheduleList(): List<Schedule> =
    mutableListOf<Schedule>()
        .apply {
            this@toScheduleList.schedules.forEach { scheduleModel ->
                addAll(scheduleModel.schedule.map { it.toSchedule() })
            }
        }

fun JournalSchedule.toSchedule(): Schedule =
    Schedule(
        UUID.randomUUID(),
        this.auditory,
        this.employee.map { it.fio },
        this.endLessonTime,
        this.lessonTime,
        this.lessonType,
        this.note,
        this.numSubgroup,
        this.startLessonTime,
        this.subject,
        this.weekNumber,
        this.zaoch
    )

fun Schedule.toSubject(lessonsAmount: Int): Subject =
    Subject(
        lessonType = this.lessonType,
        subjectName = this.subject,
        employees =
        (StringBuilder().apply {
            this@toSubject.employee.forEach {
                append(it)
                append("/n")
            }
        }).toString(),
        lessonsAmount = lessonsAmount
    )