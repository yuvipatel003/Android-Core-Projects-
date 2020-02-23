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
                    if(!isMyServiceRunning(MyService.class)){

                        if(bindService(new Intent(this, MyService.class),,0)){
                            Toast.makeText(this,"Service Binded",Toast.LENGTH_SHORT).show();
                        }
                        startService(new Intent(this, MyService.class));
                        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"Service is running",Toast.LENGTH_SHORT).show();
                    }

                break;

            case R.id.btnStopService:
                if(isMyServiceRunning(MyService.class)) {
                    stopService(new Intent(this, MyService.class));
                    Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Service already Stopped", Toast.LENGTH_SHORT).show();
                }
                break;

                default:

        }
    }

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
