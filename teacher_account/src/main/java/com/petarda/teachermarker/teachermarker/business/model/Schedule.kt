package com.petarda.teachermarker.teachermarker.business.model

import java.util.*

data class Schedule(
    val id: UUID,
    
    val auditory: List<String>,

    val employee: List<String>,
    
    val endLessonTime: String,

    val lessonTime: String,

    val lessonType: String,

    val note: String,

    val numSubgroup: Int,

    val startLessonTime: String,

    val subject: String,

    val weekNumber: List<Int>,

    val zaoch: Boolean
)