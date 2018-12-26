package com.petarda.teachermarker.teachermarker.api.model

data class Employee(
    val academicDepartment: List<String>,
    val calendarId: String,
    val fio: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String
)