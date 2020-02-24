package com.yuvrajpatel.androidservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MyService extends Service {

    // Media player object to play song on service start
    private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting song from resource
        mMediaPlayer = MediaPlayer.create(this,
                R.raw.song);

        //setting loop play to true
        //this will make the song continuously playing
        mMediaPlayer.setLooping(true);

        //staring the player
        mMediaPlayer.start();

        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
        mMediaPlayer.stop();
    }

}
