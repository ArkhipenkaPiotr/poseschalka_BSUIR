package com.petarda.teachermarker.teachermarker.api.model

data class ExamSchedule(
    val schedule: List<ExamScheduleModel>,
    val weekDay: String
)