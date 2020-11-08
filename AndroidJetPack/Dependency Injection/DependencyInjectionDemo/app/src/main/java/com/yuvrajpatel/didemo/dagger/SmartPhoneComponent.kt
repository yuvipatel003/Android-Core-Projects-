package com.yuvrajpatel.didemo.dagger

import com.anushka.didemo.SmartPhone
import com.yuvrajpatel.didemo.model.BatteryModule
import dagger.Component

@Component(modules = [BatteryModule::class])
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}