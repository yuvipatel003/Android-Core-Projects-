package com.anushka.didemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yuvrajpatel.didemo.dagger.DaggerSmartPhoneComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
