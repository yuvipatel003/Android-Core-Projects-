package com.yuvrajpatel.didemo

import android.app.Application
import com.yuvrajpatel.didemo.dagger.DaggerSmartPhoneComponent
import com.yuvrajpatel.didemo.dagger.SmartPhoneComponent
import com.yuvrajpatel.didemo.model.BatteryModule

class App : Application() {

    lateinit var smartPhoneComponent: SmartPhoneComponent
    @Override
    override fun onCreate() {
        smartPhoneComponent = intDagger()
        super.onCreate()
    }

    fun intDagger(): SmartPhoneComponent = DaggerSmartPhoneComponent.builder()
            .batteryModule(BatteryModule(5000))
            .build()
}