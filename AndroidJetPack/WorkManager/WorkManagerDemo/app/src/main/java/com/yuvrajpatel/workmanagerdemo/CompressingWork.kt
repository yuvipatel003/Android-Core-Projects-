package com.yuvrajpatel.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class CompressingWork(context : Context, param : WorkerParameters) : Worker(context, param) {

    val TAG = CompressingWork::class.java.simpleName
    override fun doWork(): Result {
        try {

            for (i in 1..700) {
                Log.d(TAG, "Compressing : $i")
            }
            return Result.success()
        } catch (e: Exception) {
            Log.d(TAG, "Error : ${e.toString()}")
        }
        return Result.failure()
    }
}