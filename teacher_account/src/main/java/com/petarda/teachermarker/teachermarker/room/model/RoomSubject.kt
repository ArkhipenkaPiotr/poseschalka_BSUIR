package com.petarda.teachermarker.teachermarker.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RoomSubject(
    @PrimaryKey
    var id: Long? = null,
    val lessonType: String,
    val subjectName: String,
    val employees: String,
    val lessonsAmount: Int
)