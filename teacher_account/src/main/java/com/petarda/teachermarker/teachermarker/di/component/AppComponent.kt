package com.petarda.teachermarker.teachermarker.di.component

import com.petarda.teachermarker.teachermarker.business.interactor.authentication.AuthenticationInteractorImpl
import com.petarda.teachermarker.teachermarker.business.interactor.schedule.ScheduleInteractorImpl
import com.petarda.teachermarker.teachermarker.business.interactor.subjects.SubjectsInteractorImpl
import com.petarda.teachermarker.teachermarker.business.interactor.user_preference.preferences.LocalUserSharedPreferencesRepositoryImpl
import com.petarda.teachermarker.teachermarker.di.module.ApiModule
import com.petarda.teachermarker.teachermarker.di.module.AppModule
import com.petarda.teachermarker.teachermarker.di.module.BusinessModule
import com.petarda.teachermarker.teachermarker.di.module.DatabaseModule
import com.petarda.teachermarker.teachermarker.screen.authentication_base.AuthenticationViewModel
import com.petarda.teachermarker.teachermarker.screen.find_subject.FindSubjectViewModel
import com.petarda.teachermarker.teachermarker.screen.login.LoginViewModel
import com.petarda.teachermarker.teachermarker.screen.register.RegisterViewModel
import com.petarda.teachermarker.teachermarker.screen.subjects.SubjectsViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, BusinessModule::class, DatabaseModule::class, ApiModule::class])
@Singleton
interface AppComponent {

    fun inject(localUserSharedPreferencesRepositoryImpl: LocalUserSharedPreferencesRepositoryImpl)
    fun inject(authenticationInteractorImpl: AuthenticationInteractorImpl)
    fun inject(authenticationViewModel: AuthenticationViewModel)
    fun inject(registerViewModel: RegisterViewModel)
    fun inject(loginViewModel: LoginViewModel)
    fun inject(scheduleInteractorImpl: ScheduleInteractorImpl)
    fun inject(findSubjectViewModel: FindSubjectViewModel)
    fun inject(subjectsInteractorImpl: SubjectsInteractorImpl)
    fun inject(subjectsViewModel: SubjectsViewModel)
}