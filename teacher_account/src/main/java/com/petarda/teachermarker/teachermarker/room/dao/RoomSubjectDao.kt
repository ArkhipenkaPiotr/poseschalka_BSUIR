package com.petarda.teachermarker.teachermarker.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.petarda.teachermarker.teachermarker.room.model.RoomSubject
import io.reactivex.Flowable

@Dao
interface RoomSubjectDao {

    @Insert
    fun insertSubject(roomSubject: RoomSubject): Long

    @Query("SELECT * from RoomSubject")
    fun getAllSubjects(): Flowable<List<RoomSubject>>

    @Query("SELECT * from RoomSubject where id=:id")
    fun getSubjectById(id: Long): Flowable<RoomSubject>
}