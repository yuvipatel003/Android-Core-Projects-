package com.yuvrajpatel.tmdbdashboard.ui.actor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor
import com.yuvrajpatel.tmdbdashboard.databinding.ListItemActorBinding

class ActorAdapter :RecyclerView.Adapter<ActorViewHolder>() {

    private val actorList = ArrayList<Actor>()

    fun setList(actors: List<Actor>){
        actorList.clear()
        actorList.addAll(actors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemActorBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_actor,
            parent,
            false
        )
        return ActorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actorList.get(position))
    }
}

class ActorViewHolder (val binding : ListItemActorBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(actor : Actor){
        binding.textViewActorName.text = actor.name
        binding.textViewActorPopularity.text = actor.popularity.toString()

        val profileUrl =   "https://image.tmdb.org/t/p/w500" + actor.profile_path

        Glide.with(binding.imageViewActor.context)
            .load(profileUrl)
            .into(binding.imageViewActor)
    }
}