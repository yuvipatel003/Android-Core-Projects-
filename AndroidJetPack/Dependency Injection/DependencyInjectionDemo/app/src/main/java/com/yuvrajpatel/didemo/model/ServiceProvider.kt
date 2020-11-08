package com.yuvrajpatel.didemo.model

import android.util.Log
import javax.inject.Inject

class ServiceProvider @Inject constructor(){
    init {
        Log.i("DIDEMO","Service Provider Constructed")
    }

    fun getServiceProvider(){
        Log.i("DIDEMO","Service provider connected")
    }
}