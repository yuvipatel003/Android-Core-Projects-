package com.yuvrajpatel.jobschedulerjobservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class JobSchedulerService extends JobService {

    private static String TAG = "Job_Scheduler_Service";

    public JobSchedulerService(){

    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "Scheduled Service Started", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Scheduled Service Started");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Toast.makeText(this, "Scheduled Service Stopped", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Scheduled Service Stopped");
        return false;
    }
}
