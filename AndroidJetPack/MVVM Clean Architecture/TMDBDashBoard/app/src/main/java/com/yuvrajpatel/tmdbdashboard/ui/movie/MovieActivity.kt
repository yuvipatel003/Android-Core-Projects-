package com.yuvrajpatel.tmdbdashboard.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }
}