package com.example.movieappmvi.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappmvi.databinding.MovieLayoutBinding
import com.example.movieappmvi.model.ResultMovie
import com.example.movieappmvi.util.Constants
import com.squareup.picasso.Picasso

class MovieAdapter : ListAdapter<ResultMovie, MovieAdapter.MovieViewHolder>(DiffCallBack()) {
    lateinit var onClick: (ResultMovie) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<ResultMovie>() {
        override fun areItemsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        return MovieViewHolder(
            MovieLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: MovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ResultMovie) {
            binding.apply {
                textView.text = result.original_title
                textRank.text = result.vote_average.toString()
                textLan.text = "Language ${result.original_language}"

                val image = "${Constants.BASE_IMG}${result.poster_path}"
                Picasso.get().load(image).into(binding.imageView)
            }
            itemView.setOnClickListener {
                onClick.invoke(result)
            }
        }
    }
}