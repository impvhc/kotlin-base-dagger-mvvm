package com.impvhc.baseapp.ui.splash

import com.impvhc.baseapp.AppSharedPreferences
import com.impvhc.core.CoreViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by victor on 2/13/18.
 */
class SplashViewModel
@Inject constructor(): CoreViewModel() {

    @Inject
    lateinit var shared : AppSharedPreferences

    private val message: PublishSubject<String> = PublishSubject.create()

    fun getStatus() {
        var splashTimer: Disposable = Disposables.disposed()
        splashTimer = Observable.create<Int> {
            if(shared.getMemberID().equals("0")){
                it.onNext(0)
            }else{
                it.onNext(1)
            }
            it.onComplete()
        }.delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { i ->  message.onNext("Welcome") }
        addDisposable(splashTimer)
    }

    fun fetchSnackBarMessage(): Observable<String> = message.hide()

}