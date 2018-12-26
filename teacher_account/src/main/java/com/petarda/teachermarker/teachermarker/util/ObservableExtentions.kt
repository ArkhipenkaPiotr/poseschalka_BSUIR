package com.petarda.teachermarker.teachermarker.util

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

fun <T1, T2, R> Observable<T1>.flatZipWith(
    observable: Observable<T2>,
    function: BiFunction<T1, T2, Observable<R>>
): Observable<R> {
    return Observable.merge(Observable.zip(this, observable, function))
}