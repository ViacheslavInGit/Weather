package com.bilyi.viacheslav.weather.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    // подписка - результат вызова subscribe у Observable, Flowable, Single, Maybe, Completable
    // если ее очистить (.dispose()) то все операции в ней прекратяться а ресурсы - отчистятся
    private val disposable = CompositeDisposable()

    // метод для наследников
    // сохраняеться подписка
    // указывает шедулеры для операций цепочки.

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

    // вызовется когда вью модель отчиститься
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}

// До подписки операции выполняються в шедулере io
// После - в главном потоке
fun <T> Single<T>.prepareSchedulers() =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())