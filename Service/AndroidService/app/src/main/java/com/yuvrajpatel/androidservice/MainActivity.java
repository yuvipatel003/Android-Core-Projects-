package com.yuvrajpatel.androidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartService, mBtnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStartService = findViewById(R.id.btnStartService);
        mBtnStopService = findViewById(R.id.btnStopService);

        mBtnStartService.setOnClickListener(this);
        mBtnStopService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id){
            case R.id.btnStartService:
                    // Check for MyService is running or not
                    if(!isMyServiceRunning(MyService.class)){
                        // If MyService is not running then start it and notify user
                        startService(new Intent(this, MyService.class));
                        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();
                    }else {
                        // If MyService is already running then notify user
                        Toast.makeText(this,"Service is running",Toast.LENGTH_SHORT).show();
                    }

                break;

            case R.id.btnStopService:
                // Check for MyService is running or not
                if(isMyServiceRunning(MyService.class)) {
                    // If MyService is running then stop it and notify user
                    stopService(new Intent(this, MyService.class));
                    Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
                }else {
                    // If MyService is not running then notify user
                    Toast.makeText(this, "Service already Stopped", Toast.LENGTH_SHORT).show();
                }
                break;

                default:

        }
    }

    /**
     * Check if service is running or not
     * @param serviceClass
     * if Service is running @return true otherwise false
     */
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
