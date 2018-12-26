package com.petarda.teachermarker.teachermarker.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.petarda.teachermarker.teachermarker.room.dao.RoomSubjectDao
import com.petarda.teachermarker.teachermarker.room.dao.RoomUserDao
import com.petarda.teachermarker.teachermarker.room.model.RoomSubject
import com.petarda.teachermarker.teachermarker.room.model.RoomUser

@Database(entities = [RoomUser::class, RoomSubject::class], version = 1)
abstract class TeacherMarkerDatabase : RoomDatabase() {
    abstract fun roomUserDao(): RoomUserDao
    abstract fun roomSubjectDao(): RoomSubjectDao
}