package com.yuvrajpatel.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UploadWork(context : Context, param : WorkerParameters) : Worker(context, param) {

    companion object {
        val KEY_OUTPUT_DATA = "KEY_OUTPUT"
    }
    val TAG = UploadWork::class.java.simpleName
    override fun doWork(): Result {
        try {
            val receiveInput = inputData.getInt(MainActivity.KEY_INPUT_DATA, 0)

            for (i in 1..receiveInput) {
                Log.d(TAG, "Uploading : $i")
                Thread.sleep(20)
            }

            val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val outputdata = time.format(Date())

            Log.d(TAG,"Completed: $outputdata")
            val data = Data.Builder()
                .putString(KEY_OUTPUT_DATA, outputdata)
                .build()

            return Result.success(data)
        } catch (e: Exception) {
            Log.d(TAG, "Error : ${e.toString()}")
        }
        return Result.failure()
    }
}