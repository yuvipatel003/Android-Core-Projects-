package com.yuvrajpatel.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ACTIVITY_NOTIFICATION";
    private Button mBtnSendNotification, mBtnUpdateNotification, mBtnCancelNotification;
    private static final String PRIMARY_CHANNEL_ID = "my_notification_channel";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.yuvrajpatel.notification.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_DELETE_NOTIFICATION = "com.yuvrajpatel.notification.ACTION_DELETE_NOTIFICATION";
    private static final String ACTION_CLICK_NOTIFICATION = "com.yuvrajpatel.notification.ACTION_CLICK_NOTIFICATION";
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private NotificationResponseReceiver mNotificationResponseReceiver = new NotificationResponseReceiver();
    Intent deleteIntent = new Intent(ACTION_DELETE_NOTIFICATION);
    Intent clickActionIntent = new Intent(ACTION_CLICK_NOTIFICATION);
    PendingIntent deletePendingIntent, clickActionPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSendNotification = findViewById(R.id.button_sendNotification);
        mBtnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On click : send notification from application
                sendNotification();
            }
        });

        mBtnUpdateNotification = findViewById(R.id.button_updateNotification);
        mBtnUpdateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update the notification
                updateNotification();
            }
        });

        mBtnCancelNotification = findViewById(R.id.button_cancelNotification);
        mBtnCancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cancel the notification
                cancelNotification();
            }
        });

        setNotificationButtonState(true, false, false);

        setActionPendingIntent();

        registerReceiver(mNotificationResponseReceiver,new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        registerReceiver(mNotificationResponseReceiver,new IntentFilter(ACTION_DELETE_NOTIFICATION));
        registerReceiver(mNotificationResponseReceiver,new IntentFilter(ACTION_CLICK_NOTIFICATION));
    }

    /**
     * set PendingIntent to handle notification action delete and click
     */
    private void setActionPendingIntent() {

        deletePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, deleteIntent, PendingIntent.FLAG_ONE_SHOT);

        clickActionPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, clickActionIntent, PendingIntent.FLAG_ONE_SHOT);
        Log.d(TAG,"Pending Intent set successfully : Delete, Click");
    }

    /**
     * Create Notification channel and send notification
     * using NotificationManager, NotificationCompat.Builder
     */
    public void sendNotification() {
        Log.d(TAG,"Notification sent process initiated");
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getResources().getString(R.string.str_my_notification_channel));
            mNotificationManager.createNotificationChannel(notificationChannel);

            Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
            PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                    (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
            notifyBuilder.addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent);
            notifyBuilder.setDeleteIntent(deletePendingIntent);
            notifyBuilder.setContentIntent(clickActionPendingIntent);
            mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());

            setNotificationButtonState(false, true, true);
            Log.d(TAG,"Notification sent successfully");
        } else {
            Log.d(TAG,"Android version not supported : required minimum SDK Version 26");
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

    /**
     * Update notification with new title and text
     */
    public void updateNotification() {

        Log.d(TAG,"Notification update process initiated");
        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.ic_launcher_foreground);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle(getResources().getString(R.string.str_updated_notification_title)))
                .setContentText(getResources().getString(R.string.str_updated_notification_text));
        notifyBuilder.setDeleteIntent(deletePendingIntent);
        notifyBuilder.setContentIntent(clickActionPendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        setNotificationButtonState(false, false, true);
        Log.d(TAG,"Notification updated successfully");
    }

    /**
     * Cancel notification
     */
    public void cancelNotification() {
        mNotificationManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true, false, false);
        Log.d(TAG,"Notification canceled successfully");
    }

    /**
     * Set layout buttons state
     * @param isSendEnabled
     * @param isUpdateEnabled
     * @param isCancelEnabled
     */
    void setNotificationButtonState(Boolean isSendEnabled, Boolean isUpdateEnabled, Boolean isCancelEnabled) {
        mBtnSendNotification.setEnabled(isSendEnabled);
        mBtnUpdateNotification.setEnabled(isUpdateEnabled);
        mBtnCancelNotification.setEnabled(isCancelEnabled);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mNotificationResponseReceiver);
        super.onDestroy();
    }

    /**
     * NotificationResponseReceiver to receive notification actions through BroadcastReceiver
     * update notification on Update request from android notification
     * reset button state on Delete notification from android notification
     * open application on notification click from android notification
     */
    public class NotificationResponseReceiver extends BroadcastReceiver {

        public NotificationResponseReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "Broadcast Received intent action : " + intent.getAction());

            switch(intent.getAction()) {
                case ACTION_UPDATE_NOTIFICATION :
                    updateNotification();
                    break;

                case ACTION_DELETE_NOTIFICATION :
                    setNotificationButtonState(true, false, false);
                    break;

                case ACTION_CLICK_NOTIFICATION :
                    Intent newIntent = new Intent();
                    newIntent.setClassName("com.yuvrajpatel.notification", "com.yuvrajpatel.notification.MainActivity");
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(newIntent);
                    Toast.makeText(getApplicationContext(),"Welcome back in Application through notification", Toast.LENGTH_SHORT).show();
                    break;

                    default:
                        Log.d(TAG, "Unhandled case");
            }

        }
    }
}

