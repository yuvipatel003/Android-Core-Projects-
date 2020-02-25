package com.yuvrajpatel.jobschedulerjobservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class JobSchedulerService extends JobService {

    public JobSchedulerService(){

    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "Scheduled Service Started", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Toast.makeText(this, "Scheduled Service Stopped", Toast.LENGTH_SHORT).show();
        return false;
    }
}
