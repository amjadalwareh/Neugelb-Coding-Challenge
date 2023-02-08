package com.neugelb.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.neugelb.domain.entity.Movie
import com.neugelb.presentation.databinding.ViewholderMovieBinding
import com.neugelb.presentation.utils.load

class MoviesAdapter(
    private val onClickMovie: (Movie?) -> Unit
) : PagingDataAdapter<Movie, MoviesAdapter.ViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
        }
    }

    inner class ViewHolder constructor(
        private val view: ViewholderMovieBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun onBind(item: Movie?) = with(view) {
            poster.load(item?.posterPath)
            title.text = item?.originalTitle
            overview.text = item?.overview
            releaseDate.text = item?.releaseDate
            movie.setOnClickListener {
                onClickMovie(item)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}