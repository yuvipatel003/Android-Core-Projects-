package com.yuvrajpatel.didemo.dagger

import com.yuvrajpatel.didemo.MainActivity
import com.yuvrajpatel.didemo.model.BatteryModule
import com.yuvrajpatel.didemo.model.SandDiskMemoryCardModule
import dagger.Component

@Component(modules = [BatteryModule::class, SandDiskMemoryCardModule::class])
interface SmartPhoneComponent {
    fun inject(mainActivity: MainActivity)
}