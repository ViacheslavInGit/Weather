package com.bilyi.viacheslav.weather.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    fun <T> subscribe(
        chain: Single<T>,
        subscribeAction: (T) -> Unit,
        errorAction: (Throwable) -> Unit
    ) {
        disposable.add(
            chain.prepareSchedulers()
                .subscribe(
                    subscribeAction,
                    errorAction
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}

fun <T> Single<T>.prepareSchedulers() =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())