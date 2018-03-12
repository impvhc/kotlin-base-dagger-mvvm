package com.impvhc.core

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by victor on 2/13/18.
 */
abstract class CoreViewModel: ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    fun clearSubscriptions() {
        disposables.clear()
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}