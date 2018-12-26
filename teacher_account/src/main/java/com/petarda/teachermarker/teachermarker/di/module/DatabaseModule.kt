package com.petarda.teachermarker.teachermarker.di.module

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.room.TeacherMarkerDatabase
import com.petarda.teachermarker.teachermarker.room.dao.RoomSubjectDao
import com.petarda.teachermarker.teachermarker.room.dao.RoomUserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): TeacherMarkerDatabase {
        return Room.databaseBuilder(
            context,
            TeacherMarkerDatabase::class.java,
            context.getString(R.string.database_name)
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomUserDao(database: TeacherMarkerDatabase): RoomUserDao =
            database.roomUserDao()

    @Provides
    @Singleton
    fun provideRoomSubjecetDao(database: TeacherMarkerDatabase): RoomSubjectDao =
            database.roomSubjectDao()
}