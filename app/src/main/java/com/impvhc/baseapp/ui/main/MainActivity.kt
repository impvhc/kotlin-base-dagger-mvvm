package com.impvhc.baseapp.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.impvhc.baseapp.R
import com.impvhc.baseapp.ui.level.LevelActivity
import com.impvhc.core.CoreActivity
import com.impvhc.core.util.goTo
import org.jetbrains.anko.info
import javax.inject.Inject

class MainActivity : CoreActivity() {

    lateinit var viewModel: MainViewModel
    var test: String = "";
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        info {
            "Test: "+viewModel.getMessage()
        }
        info {
            "Test-a: "+test
        }
        goTo<LevelActivity>()
    }

    override fun onPause() {
        super.onPause()
        info {
            "Test: "+viewModel.getMessage()
        }
        viewModel.setMessage(viewModel.getMessage()+"A");
        info {
            "Test-a: "+test
        }
        test += "B"
    }
}
