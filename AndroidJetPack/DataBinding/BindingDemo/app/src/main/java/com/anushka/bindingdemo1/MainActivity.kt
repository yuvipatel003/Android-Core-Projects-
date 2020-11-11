package com.anushka.bindingdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anushka.bindingdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setbinding()

        binding.submitButton.setOnClickListener {
            displayGreeting()
        }
    }

    private fun setbinding() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    private fun displayGreeting() {
        binding.apply {
            val message = "Hello! " + nameEditText.text
            greetingTextView.text = message
        }
    }
}
