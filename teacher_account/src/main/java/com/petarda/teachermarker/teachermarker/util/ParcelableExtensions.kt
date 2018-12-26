package com.petarda.teachermarker.teachermarker.util

import android.os.Parcel
import android.os.Parcelable

fun Parcelable.marshall(): ByteArray =
    Parcel.obtain().let {
        this.writeToParcel(it, 0)
        val bytes = it.marshall()
        it.recycle()
        bytes
    }

fun ByteArray.unmarshall(): Parcel =
    Parcel.obtain().also {
        it.unmarshall(this, 0, this.size)
        it.setDataPosition(0)
    }

fun <T> ByteArray.unmarshall(creator: Parcelable.Creator<T>): T {
    val parcel = unmarshall()
    val result = creator.createFromParcel(parcel)
    parcel.recycle()
    return result
}