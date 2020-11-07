package com.yuvrajpatel.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        val KEY_INPUT_DATA = "KEY_INPUT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Start.setOnClickListener {
            setOneTimeWorkRequest()
        }

        btn_Start_Periodic.setOnClickListener {
            setPeroidicWorkRequest()
        }
    }

    private fun setPeroidicWorkRequest() {
        val periodicWorkRequest  = PeriodicWorkRequest.Builder(DownloadingWork::class.java,15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(periodicWorkRequest)
    }

    private fun setOneTimeWorkRequest(){
        val workManager = WorkManager.getInstance(applicationContext)
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data : Data = Data.Builder()
            .putInt(KEY_INPUT_DATA, 100)
            .build()

        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadWork::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()


        val filteringWorkrequest = OneTimeWorkRequest.Builder(FilteringWork::class.java)
            .build()
        val compressingWorkrequest = OneTimeWorkRequest.Builder(CompressingWork::class.java)
            .build()
        val downloadingWorkrequest = OneTimeWorkRequest.Builder(DownloadingWork::class.java)
            .build()


        val parallelWorkRequest = mutableListOf<OneTimeWorkRequest>(filteringWorkrequest, downloadingWorkrequest)

        workManager
            .beginWith(parallelWorkRequest)
            .then(compressingWorkrequest)
            .then(uploadWorkRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name

                if(it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(UploadWork.KEY_OUTPUT_DATA)
                    Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
                }
            })
    }
}