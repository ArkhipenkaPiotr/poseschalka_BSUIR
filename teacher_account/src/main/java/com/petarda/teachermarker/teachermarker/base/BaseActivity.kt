package com.petarda.teachermarker.teachermarker.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.cardpay.digipass.android.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutResId: Int

    private val hardCompositeDisposable = CompositeDisposable()
    private val liteCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate %s", this.toString())
        setContentView(layoutResId)
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart %s", this.toString())
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume %s", this.toString())
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause %s", this.toString())
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop %s", this.toString())
        liteCompositeDisposable.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy %s", this.toString())
        hardCompositeDisposable.dispose()
    }

    protected fun addLiteDisposable(disposable: Disposable) =
            liteCompositeDisposable.add(disposable)

    protected fun addHardDisposable(disposable: Disposable) =
            hardCompositeDisposable.add(disposable)

    fun <T : BaseViewModel> bindViewModel(clazz: Class<T>): T {
        val viewModel = ViewModelProviders.of(this).get(clazz)
        Timber.d("ViewModel $viewModel has created")
        return viewModel
    }
}