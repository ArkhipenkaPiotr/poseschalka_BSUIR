package com.petarda.teachermarker.teachermarker.business.interactor.user_preference.preferences

interface LocalUserSharedPreferencesRepository {
    fun setCurrentUserId(id: Long)
    fun getCurrentUserId(): Long
    fun resetCurrentUserId()
}