package com.petarda.teachermarker.teachermarker.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.petarda.teachermarker.teachermarker.room.model.RoomUser
import io.reactivex.Single

@Dao
interface RoomUserDao {

    @Query("SELECT * from roomuser where id=:userId")
    fun getByIdSingle(userId: Long): Single<RoomUser>

    @Query("SELECT * from roomuser where id=:userId")
    fun getById(userId: Long): RoomUser

    @Query("SELECT * from roomuser where userName=:userName")
    fun getUserByUserName(userName: String): RoomUser

    @Insert(onConflict = REPLACE)
    fun insert(roomUser: RoomUser): Long

    @Delete
    fun deleteUser(roomUser: RoomUser)
}