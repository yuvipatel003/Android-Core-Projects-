package com.yuvrajpatel.didemo.model

import com.anushka.didemo.Battery
import dagger.Module
import dagger.Provides

@Module
class BatteryModule {

    @Provides
    fun providesBattery() : Battery {
        return Battery()
    }
}