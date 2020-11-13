package com.yuvrajpatel.tmdbdashboard.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityHomeBinding
import com.yuvrajpatel.tmdbdashboard.ui.movie.MovieActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityHomeBinding
    private lateinit var mModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        mModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
//        mModel.setContext(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
//        mBinding.homeViewModel = mModel
//        mBinding.lifecycleOwner = this

        mBinding.btnMovies.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

    }
}