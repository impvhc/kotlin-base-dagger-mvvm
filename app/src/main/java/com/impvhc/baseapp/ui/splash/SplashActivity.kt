package com.impvhc.baseapp.ui.splash

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.impvhc.baseapp.R
import com.impvhc.baseapp.ui.main.MainActivity
import com.impvhc.core.CoreActivity
import com.impvhc.core.util.resetTo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : CoreActivity() {

    lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
        setUp()
    }

    private fun setUp(){
        disposables.add(viewModel.fetchSnackBarMessage().subscribe {
            showSnackBar(it)
            goNext()
        })
        viewModel.getStatus()
    }

    private fun goNext(){
        resetTo<MainActivity>()
    }

    private fun showSnackBar(message: String){
        var snackbar : Snackbar = Snackbar.make(splash_layout,message,Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
