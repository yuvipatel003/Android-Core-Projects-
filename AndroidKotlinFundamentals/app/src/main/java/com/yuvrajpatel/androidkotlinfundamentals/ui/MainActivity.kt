package com.yuvrajpatel.androidkotlinfundamentals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yuvrajpatel.androidkotlinfundamentals.R
import com.yuvrajpatel.androidkotlinfundamentals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        activityBinding.textViewTitle.text = "Yuvraj Patel"

    }
}
