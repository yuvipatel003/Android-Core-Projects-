package com.yuvrajpatel.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskJobScheduler extends JobService {


    private static final String TAG = "AsyncTaskJobScheduler";
    JobParameters mJobParameters;
    Boolean mJobCancelled = false;
    JobSchedulerAsyncTask mJobSchedulerAsyncTask;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Log.d(TAG,"Job Started");

        this.mJobParameters = jobParameters;
        mJobSchedulerAsyncTask = new JobSchedulerAsyncTask();
        mJobSchedulerAsyncTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG,"Job Cancelled");
        this.mJobCancelled = mJobSchedulerAsyncTask.cancel(true);
        return false;
    }

    private class JobSchedulerAsyncTask extends AsyncTask<Void, Void ,Void>{

        @Override
        protected void onPreExecute() {
            Log.d(TAG + " AsyncTask", "Task initiated");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG + " AsyncTask", "Task execution started");

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    if(mJobCancelled){
                        Log.d(TAG + " AsyncTask", "JobCancelled");
                        return null;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d(TAG + " AsyncTask", "Completed");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d(TAG + " AsyncTask", "call jobFinished");
            jobFinished(mJobParameters, false);
            super.onPostExecute(aVoid);
        }
    }
}
