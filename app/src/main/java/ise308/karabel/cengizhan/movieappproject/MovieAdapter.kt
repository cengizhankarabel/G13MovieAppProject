package ise308.karabel.cengizhan.movieappproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val moveList: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ListItemHolder>(){

    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {


        internal var title = view.findViewById<View>(R.id.textView_movie_title) as TextView
        internal var categorie =
                view.findViewById<View>(R.id.textView_movie_categorie) as TextView

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val intentToMoviePager = Intent(view!!.context, MoviePagerActivity::class.java)
            view.context.startActivity(intentToMoviePager)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_movie, parent, false)
        return ListItemHolder(itemView)

    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {



        val movie = moveList[position]
        holder.title.text = movie.title
        when {
            movie.action ->holder.categorie.text = "Action"
            movie.horror ->holder.categorie.text = "Horror"
            movie.comedy ->holder.categorie.text = "Comedy"
            movie.drama ->holder.categorie.text = "Drama"
            movie.thriller ->holder.categorie.text = "Thriller"
            movie.western ->holder.categorie.text = "Western"
        }
    }


    override fun getItemCount(): Int {
        if (moveList != null){
            return moveList.size
        }
        return -1
    }



}