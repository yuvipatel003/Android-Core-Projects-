package com.yuvrajpatel.tmdbdashboard.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityMovieBinding
import com.yuvrajpatel.tmdbdashboard.ui.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel

    private lateinit var mBinding: ActivityMovieBinding
    private lateinit var mAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.update,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_update -> {
                updatePopularMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun initRecyclerView() {
        mBinding.progressBarMovie.visibility = View.VISIBLE
        mBinding.recyclerViewMovie.layoutManager = LinearLayoutManager(this)
        mAdapter = MovieAdapter()
        mBinding.recyclerViewMovie.adapter = mAdapter
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        val movieLiveData = movieViewModel.getMovies()
        movieLiveData.observe(this, Observer {
            if(it!=null) {
                mAdapter.setList(it)
                mAdapter.notifyDataSetChanged()
                mBinding.progressBarMovie.visibility = View.GONE
            }
        })
    }

    private fun updatePopularMovies() {
        mBinding.progressBarMovie.visibility = View.VISIBLE
        val movieLiveData = movieViewModel.updateMovies()
        movieLiveData.observe(this, Observer {
            if(it!=null) {
                mAdapter.setList(it)
                mAdapter.notifyDataSetChanged()
                mBinding.progressBarMovie.visibility = View.GONE
            } else {
                mBinding.progressBarMovie.visibility = View.GONE
            }
        })
    }
}