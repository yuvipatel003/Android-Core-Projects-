package com.yuvrajpatel.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnScheduleJob, mBtnCancelJob, mBtnScheduleJobAsyncTask;
    private JobScheduler mScheduler, mAsyncScheduler;
    private static final int JOB_ID = 0;
    private static final int ASYNC_JOB_ID = 1;
    //Switches for setting job options
    private SwitchCompat mDeviceIdleSwitch;
    private SwitchCompat mDeviceChargingSwitch;
    private SeekBar mSeekBarDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnScheduleJob = findViewById(R.id.btn_schedule_job);
        mBtnScheduleJobAsyncTask = findViewById(R.id.btn_schedule_job_asynctask);
        mBtnCancelJob = findViewById(R.id.btn_cancel_job);

        mBtnScheduleJob.setOnClickListener(this);
        mBtnScheduleJobAsyncTask.setOnClickListener(this);
        mBtnCancelJob.setOnClickListener(this);

        mDeviceIdleSwitch = findViewById(R.id.switch_device_idle);
        mDeviceChargingSwitch = findViewById(R.id.switch_device_charging);

        mSeekBarDelay = findViewById(R.id.seekbar_delay);
        mSeekBarDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView seekBarTitle = findViewById(R.id.txt_seekbar_title);
                if(i > 0) {
                    seekBarTitle.setText(getResources().getString(R.string.str_schedule_job_by) + " " + String.valueOf(i/2) + "seconds");
                } else {
                    seekBarTitle.setText(getResources().getString(R.string.str_schedule_job_by) + " " + "Not Set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btn_schedule_job:
                scheduleJob();
                break;

            case R.id.btn_schedule_job_asynctask:
                scheduleJobAsyncTask();
                break;

            case R.id.btn_cancel_job:
                cancelJob();
                break;

                default:
        }

    }

    /**
     * Schedule job to send notification
     */
    public void scheduleJob() {
        RadioGroup networkOptions = findViewById(R.id.radiogroup_network);
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
        int seekBarInteger = mSeekBarDelay.getProgress() / 2;
        boolean isSeekBarSet = seekBarInteger > 0;

        switch(selectedNetworkID){
            case R.id.radio_network_none:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.radio_network_any:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.radio_network_wifi:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
        builder.setRequiredNetworkType(selectedNetworkOption);
        builder.setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked());
        builder.setRequiresCharging(mDeviceChargingSwitch.isChecked());

        if (isSeekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }

        boolean constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE)
                && mDeviceChargingSwitch.isChecked() && mDeviceIdleSwitch.isChecked() && isSeekBarSet;

        if(constraintSet) {
            //Schedule the job and notify the user
            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);
            Toast.makeText(this, "Job Scheduled, job will run when " +
                    "the constraints are met.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please set all constraint",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Schedule async task which will notify when finished.
     * And it will execute after 10 seconds if not cancelled before 10 sec.
     */
    public void scheduleJobAsyncTask() {

        mAsyncScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName serviceName = new ComponentName(getPackageName(),
                AsyncTaskJobScheduler.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(ASYNC_JOB_ID, serviceName);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked());
        builder.setRequiresCharging(mDeviceChargingSwitch.isChecked());
        builder.setPeriodic(15 * 60 * 1000);
            //Schedule the job and notify the user
        JobInfo myJobInfo = builder.build();
        mAsyncScheduler.schedule(myJobInfo);
        Toast.makeText(this, "AsyncTask JobScheduled", Toast.LENGTH_SHORT).show();
    }


    /**
     * Cancel both Scheduled jobs
     */
    public void cancelJob(){
        if (mScheduler!=null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        } else if (mAsyncScheduler!=null){
            mAsyncScheduler.cancelAll();
            mAsyncScheduler = null;
            Toast.makeText(this, "AsyncScheduler Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}
