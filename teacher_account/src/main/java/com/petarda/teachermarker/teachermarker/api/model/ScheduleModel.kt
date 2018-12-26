package com.petarda.teachermarker.teachermarker.api.model

data class ScheduleModel(
    val schedule: List<JournalSchedule>,
    val weekDay: String
)