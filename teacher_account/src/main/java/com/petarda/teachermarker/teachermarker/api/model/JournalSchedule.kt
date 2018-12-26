package com.petarda.teachermarker.teachermarker.api.model

data class JournalSchedule(
    val auditory: List<String>,
    val employee: List<Employee>,
    val endLessonTime: String,
    val lessonTime: String,
    val lessonType: String,
    val note: String,
    val numSubgroup: Int,
    val startLessonTime: String,
    val studentGroup: List<String>,
    val subject: String,
    val weekNumber: List<Int>,
    val zaoch: Boolean
)