package com.yuvrajpatel.coroutinesdemo

import kotlinx.coroutines.*

/**
 * Structured Concurreny
 */
class UserManagerStructured {

    var count = 0
    lateinit var deffered : Deferred<Int>
    suspend fun getUserCount() : Int {


        coroutineScope {
                    launch(Dispatchers.IO) {
                        delay(1000)
                        count = 50
                    }
             deffered = async(Dispatchers.IO) {
                delay(3000)
                return@async 70
            }
        }
        return count + deffered.await()
    }
}