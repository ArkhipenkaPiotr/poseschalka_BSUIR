package com.petarda.teachermarker.teachermarker.business.model

data class Subject (
    var id: Long? = null,
    val lessonType: String,
    val subjectName: String,
    val employees: String,
    val lessonsAmount: Int
)