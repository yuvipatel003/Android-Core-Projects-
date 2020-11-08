package com.yuvrajpatel.didemo.model

import android.util.Log
import javax.inject.Inject

class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    init {
        Log.i("DIDEMO", "SmartPhone Constructed")
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailablity()
    }

    fun makeACallWithRecording() {
        Log.i("DIDEMO", "Calling.....")
    }
}