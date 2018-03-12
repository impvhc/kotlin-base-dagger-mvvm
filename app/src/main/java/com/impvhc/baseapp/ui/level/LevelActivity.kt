package com.impvhc.baseapp.ui.level

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.impvhc.baseapp.R
import com.impvhc.core.CoreActivity
import org.jetbrains.anko.info
import javax.inject.Inject

class LevelActivity : CoreActivity() {
    lateinit var viewModel: LevelViewModel
    var test: String = "";
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        viewModel = ViewModelProviders.of(this, factory).get(LevelViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        info {
            "Test: "+viewModel.getMessage()
        }
        info {
            "Test-a: "+test
        }
    }

    override fun onPause() {
        super.onPause()
        info {
            "Test: "+viewModel.getMessage()
        }
        viewModel.setMessage(viewModel.getMessage()+"L");
        info {
            "Test-a: "+test
        }
        test += "LV"
    }
}
