package com.yuvrajpatel.notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyNotification extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("Create Notification");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creating notification
                createNotification();
            }
        });
        setContentView(button);
    }

}
