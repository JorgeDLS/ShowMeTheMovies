package jdls.one.showmethemovies.main;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jdls.one.domain.model.Movie
import jdls.one.showmethemovies.R
import jdls.one.showmethemovies.util.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

  private var movieList: MutableList<Movie> = ArrayList()

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = movieList[position]
    holder.titleTV.text = movie.title
    holder.ratingTV.text = movie.voteAverage.toString()
    holder.movieIV.loadImage(movie.backDropUrl)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater
      .from(parent.context)
      .inflate(R.layout.item_movie, parent, false)
    return ViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return movieList.size
  }

  fun addMovies(movies: List<Movie>) {
    val listSize = movieList.size
    movieList.addAll(movies)
    if (listSize > 0) notifyItemRangeChanged(listSize, movies.size) else notifyDataSetChanged()
  }

  inner class ViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer

}