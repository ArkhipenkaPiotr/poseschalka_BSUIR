package com.petarda.teachermarker.teachermarker.api.model

data class Journal(
    val currentWeekNumber: Int,
    val employee: Any,
    val examSchedules: List<ExamSchedule>,
    val schedules: List<ScheduleModel>,
    val studentGroup: StudentGroup,
    val todayDate: String,
    val todaySchedules: List<ScheduleModel>,
    val tomorrowDate: String,
    val tomorrowSchedules: List<ScheduleModel>
)