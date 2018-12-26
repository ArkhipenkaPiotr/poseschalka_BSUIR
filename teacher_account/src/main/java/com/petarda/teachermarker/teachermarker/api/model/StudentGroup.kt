package com.petarda.teachermarker.teachermarker.api.model

data class StudentGroup(
    val calendarId: String,
    val course: Int,
    val facultyId: Int,
    val id: Int,
    val name: String,
    val specialityDepartmentEducationFormId: Int
)