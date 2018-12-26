package com.cardpay.digipass.android.base

import android.arch.lifecycle.ViewModel
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val intentSubject: BehaviorSubject<Intent> = BehaviorSubject.create()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun intent(): Observable<Intent> = intentSubject

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}