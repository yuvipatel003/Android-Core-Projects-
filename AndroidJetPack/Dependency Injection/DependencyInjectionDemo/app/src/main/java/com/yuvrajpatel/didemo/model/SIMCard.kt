package com.yuvrajpatel.didemo.model

import android.util.Log
import com.yuvrajpatel.didemo.model.ServiceProvider
import javax.inject.Inject

class SIMCard @Inject constructor(private  val serviceProvider: ServiceProvider) {
    init {
        Log.i("DIDEMO","SIM Card Constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}