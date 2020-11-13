package com.yuvrajpatel.tmdbdashboard.ui.actor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityActorBinding

class ActorActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityActorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_actor)
    }
}