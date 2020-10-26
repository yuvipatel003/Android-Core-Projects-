package com.yuvrajpatel.coroutinesdemo

import kotlinx.coroutines.*

/**
 * Unstructed concurrency
 */
class UserManger {

    suspend fun getUserCount() : Int {
        var count = 0;
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 50
        }

        val deffered = CoroutineScope(Dispatchers.IO).async {
            delay(3000)
            return@async 70
        }
        return count + deffered.await()
    }
}