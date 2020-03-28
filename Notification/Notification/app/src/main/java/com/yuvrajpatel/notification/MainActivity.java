package com.yuvrajpatel.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity {

    private Button mBtnCreateNotification;
    private static final String PRIMARY_CHANNEL_ID = "my_notification_channel";
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCreateNotification = findViewById(R.id.notify);
        mBtnCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On click : send notification from application
                createNotification();
            }
        });
    }

    /**
     * Create Notification channel and send notification
     * using NotificationManager, NotificationCompat.Builder
     */
    public void createNotification() {
        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    getResources().getString(R.string.app_name), NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getResources().getString(R.string.str_my_notification_channel));
            mNotificationManager.createNotificationChannel(notificationChannel);

            NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
            mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        }
    }

    /**
     * Create Notification Builder,
     * Set Notification Title, Message, Icon, Priority
     * @return created Notification builder
     */
    public NotificationCompat.Builder getNotificationBuilder() {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle(getResources().getString(R.string.str_notification_title))
                .setContentText(getResources().getString(R.string.str_notification_text))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notificationBuilder;
    }
}

