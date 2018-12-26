package com.petarda.teachermarker.teachermarker.di.module

import android.content.Context
import android.content.SharedPreferences
import com.petarda.teachermarker.teachermarker.R
import com.petarda.teachermarker.teachermarker.business.interactor.authentication.AuthenticationInteractorImpl
import com.petarda.teachermarker.teachermarker.business.interactor.schedule.ScheduleInteractor
import com.petarda.teachermarker.teachermarker.business.interactor.schedule.ScheduleInteractorImpl
import com.petarda.teachermarker.teachermarker.business.interactor.subjects.SubjectsInteractor
import com.petarda.teachermarker.teachermarker.business.interactor.subjects.SubjectsInteractorImpl
import com.petarda.teachermarker.teachermarker.business.interactor.user_preference.preferences.LocalUserSharedPreferencesRepository
import com.petarda.teachermarker.teachermarker.business.interactor.user_preference.preferences.LocalUserSharedPreferencesRepositoryImpl
import com.vironit.fireant.business.interactor.authentication.AuthenticationInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BusinessModule {

    @Provides
    @Singleton
    fun provideLocalUserSharedPreferencesRepository(): LocalUserSharedPreferencesRepository =
        LocalUserSharedPreferencesRepositoryImpl()

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE)

    @Provides
    fun provideAuthenticationInteractor(): AuthenticationInteractor =
            AuthenticationInteractorImpl()

    @Provides
    fun provideScheduleInteractor(): ScheduleInteractor = ScheduleInteractorImpl()

    @Provides
    fun provideSubjectsInteractor(): SubjectsInteractor = SubjectsInteractorImpl()
}