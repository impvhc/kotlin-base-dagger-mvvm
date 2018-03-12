package com.impvhc.baseapp.ui.main

import com.impvhc.core.CoreViewModel
import javax.inject.Inject

/**
 * Created by victor on 2/13/18.
 */
class MainViewModel
@Inject constructor(): CoreViewModel(){
    private var message: String = ""

    fun setMessage(msg: String){
        message=msg
    }

    fun getMessage(): String = message
}