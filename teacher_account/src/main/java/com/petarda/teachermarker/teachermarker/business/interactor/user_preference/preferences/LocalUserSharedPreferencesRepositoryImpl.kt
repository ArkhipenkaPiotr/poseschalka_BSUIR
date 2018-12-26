package com.petarda.teachermarker.teachermarker.business.interactor.user_preference.preferences

import android.content.SharedPreferences
import com.petarda.teachermarker.teachermarker.TeacherMarkerApplication
import com.vironit.fireant.business.repository.local_user.preferences.exception.NoCurrentLocalUserException
import javax.inject.Inject

class LocalUserSharedPreferencesRepositoryImpl : LocalUserSharedPreferencesRepository {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private companion object {
        const val CURRENT_ID_KEY = "current_id"
    }

    init {
        TeacherMarkerApplication.instance.appComponent.inject(this)
    }

    override fun setCurrentUserId(id: Long) = sharedPreferences.edit()
        .putLong(CURRENT_ID_KEY, id)
        .apply()

    override fun getCurrentUserId(): Long {
        val id = sharedPreferences
            .getLong(CURRENT_ID_KEY, 0L)

        if (id == 0L) {
            throw NoCurrentLocalUserException()
        }
        return id
    }

    override fun resetCurrentUserId() = sharedPreferences.edit()
        .putString(CURRENT_ID_KEY, null)
        .apply()
}