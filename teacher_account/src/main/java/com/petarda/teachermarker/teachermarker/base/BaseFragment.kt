package com.cardpay.digipass.android.base

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val fragmentResId: Int

    private val hardCompositeDisposable = CompositeDisposable()
    private val liteCompositeDisposable = CompositeDisposable()
    protected lateinit var rxPermissions: RxPermissions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView %s", this.toString())
        rxPermissions = RxPermissions(this)
        return inflater.inflate(fragmentResId, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Timber.d("onAttach %s", this.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate %s", this.toString())
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

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("OnDestoyView %s", this.toString())
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("OnDetach %s", this.toString())
    }

    fun <T : BaseViewModel> bindViewModel(clazz: Class<T>): T {
        val viewModel = ViewModelProviders.of(this).get(clazz)
        Timber.d("ViewModel $viewModel has created")
        return viewModel
    }

    fun moveBack() {
        (activity as? BaseFragmentActivity)?.moveBack()
    }

    protected fun addLiteDisposable(disposable: Disposable) =
        liteCompositeDisposable.add(disposable)

    protected fun addHardDisposable(disposable: Disposable) =
        hardCompositeDisposable.add(disposable)
}