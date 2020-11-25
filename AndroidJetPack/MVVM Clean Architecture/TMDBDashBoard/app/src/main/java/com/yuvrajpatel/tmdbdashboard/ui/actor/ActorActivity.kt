package com.yuvrajpatel.tmdbdashboard.ui.actor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityActorBinding
import com.yuvrajpatel.tmdbdashboard.databinding.ActivityMovieBinding
import com.yuvrajpatel.tmdbdashboard.ui.di.Injector
import com.yuvrajpatel.tmdbdashboard.ui.movie.MovieAdapter
import com.yuvrajpatel.tmdbdashboard.ui.movie.MovieViewModel
import com.yuvrajpatel.tmdbdashboard.ui.movie.MovieViewModelFactory
import javax.inject.Inject

class ActorActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityActorBinding

    @Inject
    lateinit var factory: ActorViewModelFactory
    private lateinit var actorViewModel: ActorViewModel

    private lateinit var mAdapter : ActorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_actor)

        (application as Injector).createActorSubComponent()
            .inject(this)

        actorViewModel = ViewModelProvider(this, factory).get(ActorViewModel::class.java)

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
                updatePopularActors()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun initRecyclerView() {
        mBinding.progressBarActor.visibility = View.VISIBLE
        mBinding.recyclerViewActor.layoutManager = LinearLayoutManager(this)
        mAdapter = ActorAdapter()
        mBinding.recyclerViewActor.adapter = mAdapter
        displayPopularActors()
    }

    private fun displayPopularActors() {
        val actorLiveData = actorViewModel.getActors()
        actorLiveData.observe(this, Observer {
            if(it!=null) {
                mAdapter.setList(it)
                mAdapter.notifyDataSetChanged()
                mBinding.progressBarActor.visibility = View.GONE
            }
        })
    }

    private fun updatePopularActors() {
        mBinding.progressBarActor.visibility = View.VISIBLE
        val movieLiveData = actorViewModel.updateActors()
        movieLiveData.observe(this, Observer {
            if(it!=null) {
                mAdapter.setList(it)
                mAdapter.notifyDataSetChanged()
                mBinding.progressBarActor.visibility = View.GONE
            } else {
                mBinding.progressBarActor.visibility = View.GONE
            }
        })
    }
}