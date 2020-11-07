package com.yuvrajpatel.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class DownloadingWork(context : Context, param : WorkerParameters) : Worker(context, param) {

    val TAG = DownloadingWork::class.java.simpleName
    override fun doWork(): Result {
        try {

            for (i in 1..2000) {
                Log.d(TAG, "Downloading : $i")
            }


            val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val outputdata = time.format(Date())

            Log.d(TAG,"Completed: $outputdata")

            return Result.success()
        } catch (e: Exception) {
            Log.d(TAG, "Error : ${e.toString()}")
        }
        return Result.failure()
    }
}