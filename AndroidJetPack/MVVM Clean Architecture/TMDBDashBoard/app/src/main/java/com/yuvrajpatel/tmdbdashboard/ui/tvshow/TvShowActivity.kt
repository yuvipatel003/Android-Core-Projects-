package com.yuvrajpatel.tmdbdashboard.ui.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityTvShowBinding

class TvShowActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
    }
}