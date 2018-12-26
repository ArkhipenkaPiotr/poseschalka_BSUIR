package com.petarda.teachermarker.teachermarker.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils{
    fun fromMillisToTimeString(millis: Long) : String {
        val format = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        return format.format(millis)
    }

    fun fromMillisToDateString(millis: Long): String {
        val format = SimpleDateFormat("dd M", Locale.getDefault())
        return format.format(millis)
    }
}