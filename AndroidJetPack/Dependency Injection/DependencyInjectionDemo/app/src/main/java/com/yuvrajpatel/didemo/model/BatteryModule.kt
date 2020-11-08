package com.yuvrajpatel.didemo.model

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class BatteryModule (val sizemAh : Int){

    @Provides
    fun providesBattery() : Battery {
        Log.i("DIDEMO", "Battery capacity : $sizemAh mAh")
        return Battery()
    }
}