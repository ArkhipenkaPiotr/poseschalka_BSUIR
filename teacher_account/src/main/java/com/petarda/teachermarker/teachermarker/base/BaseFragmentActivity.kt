package com.cardpay.digipass.android.base

import android.support.annotation.IdRes
import com.petarda.teachermarker.teachermarker.base.BaseActivity


abstract class BaseFragmentActivity : BaseActivity() {

    @get:IdRes
    abstract val fragmentContainerResId: Int

    fun <T : BaseFragment> moveTo(fragment: T) {
        supportFragmentManager.beginTransaction()
                .replace(fragmentContainerResId, fragment)
                .addToBackStack(fragment.tag)
                .commit()
    }

    fun <T : BaseFragment> moveToWithoutHistory(fragment: T) {
        supportFragmentManager.beginTransaction()
                .replace(fragmentContainerResId, fragment)
                .commit()
    }

    fun moveBack() {
        supportFragmentManager.popBackStack()
    }
}