package com.anushka.didemo

import android.util.Log
import javax.inject.Inject

class MemoryCard @Inject constructor(){
    init {
        Log.i("DIDEMO","Memory Card Constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("DIDEMO","Memory space available")
    }
}