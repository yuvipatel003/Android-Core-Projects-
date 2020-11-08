package com.yuvrajpatel.didemo.model

import android.util.Log
import javax.inject.Inject

class SandDiskMemoryCard @Inject constructor() :
    MemoryCard {

    override fun getSpaceAvailablity(){
        Log.i("DIDEMO","Memory space available")
    }
}