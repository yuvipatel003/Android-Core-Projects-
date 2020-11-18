package com.yuvrajpatel.tmdbdashboard.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yuvrajpatel.tmdbdashboard.R
import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie
import com.yuvrajpatel.tmdbdashboard.databinding.ListItemMovieBinding

class MovieAdapter :RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>){
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemMovieBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_movie,
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.get(position))
    }
}

class MovieViewHolder (val binding : ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movie : Movie){
        binding.textViewMovieTitle.text = movie.title
        binding.textViewMovieDescription.text = movie.overview

        val posterUrl =   "https://image.tmdb.org/t/p/w500" + movie.poster_path

        Glide.with(binding.imageViewMovie.context)
            .load(posterUrl)
            .into(binding.imageViewMovie)

    }
}