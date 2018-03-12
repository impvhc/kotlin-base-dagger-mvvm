package com.impvhc.baseapp.ui.level

import com.impvhc.core.CoreViewModel
import javax.inject.Inject

/**
 * Created by victor on 2/16/18.
 */
class LevelViewModel
@Inject constructor(): CoreViewModel() {
    private var message: String = ""

    fun setMessage(msg: String){
        message=msg
    }

    fun getMessage(): String = message
}