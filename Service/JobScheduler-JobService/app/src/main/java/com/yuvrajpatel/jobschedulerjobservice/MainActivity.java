package com.yuvrajpatel.jobschedulerjobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";

    Button mBtnStartJobSchedulerService, mBtnStopJobSchedulerService;
    JobScheduler mJobScheduler;

    private static int SCHEDULED_JOBID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStartJobSchedulerService = findViewById(R.id.btnStartJobSchedulerService);
        mBtnStopJobSchedulerService = findViewById(R.id.btnStopJobSchedulerService);

        mJobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        mBtnStartJobSchedulerService.setOnClickListener(this);
        mBtnStopJobSchedulerService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnStartJobSchedulerService:
                ComponentName jobService =
                        new ComponentName(getPackageName(), JobSchedulerService.class.getName());
                // Set periodic will start this job after 15 minutes
                JobInfo jobInfo =
                        new JobInfo.Builder(SCHEDULED_JOBID, jobService).setPeriodic(900000, 5000).build();

                int jobId = mJobScheduler.schedule(jobInfo);
                if (mJobScheduler.schedule(jobInfo) > 0) {
                    Toast.makeText(MainActivity.this, "Successfully scheduled job: " + jobId, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG,"Failure :" + jobId);
                }

                break;

            case R.id.btnStopJobSchedulerService:
                // Stop scheduled job
                mJobScheduler.cancelAll();
                Toast.makeText(MainActivity.this, "Cancelled all scheduled jobs", Toast.LENGTH_SHORT).show();
                break;

                default:
                    Log.e(TAG,"View id does not found");
                    break;

        }
    }
}
