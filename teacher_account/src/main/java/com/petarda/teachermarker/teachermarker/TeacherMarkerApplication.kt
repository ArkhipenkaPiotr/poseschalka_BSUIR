package com.petarda.teachermarker.teachermarker

import android.app.Application
import com.petarda.teachermarker.teachermarker.di.component.AppComponent
import com.petarda.teachermarker.teachermarker.di.component.DaggerAppComponent
import com.petarda.teachermarker.teachermarker.di.module.AppModule

class TeacherMarkerApplication : Application() {
    companion object {
        lateinit var instance: TeacherMarkerApplication
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = applicationContext))
            .build()
    }
}