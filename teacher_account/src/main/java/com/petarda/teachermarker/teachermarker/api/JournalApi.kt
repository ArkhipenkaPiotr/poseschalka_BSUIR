package com.petarda.teachermarker.teachermarker.api

import com.petarda.teachermarker.teachermarker.api.model.Journal
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JournalApi {
    @GET("/api/v1/studentGroup/schedule")
    fun getStudentsJournal(@Query("studentGroup") studentGroup: String): Single<Journal>
}