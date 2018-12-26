package com.petarda.teachermarker.teachermarker.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RoomUser(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var userName: String,
    var password: String?
)