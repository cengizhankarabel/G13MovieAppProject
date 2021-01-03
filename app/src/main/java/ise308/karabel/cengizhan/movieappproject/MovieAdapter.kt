package ise308.karabel.cengizhan.movieappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieAdapter(private val mainActivity: MainActivity,
                   private val movieList: ArrayList<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ListItemHolder>() {



    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener{



        internal var title = view.findViewById<View>(R.id.textView_movie_title) as TextView
        internal var category = view.findViewById<View>(R.id.textView_movie_category) as TextView
        internal var listNumber = view.findViewById<View>(R.id.list_number) as TextView
        internal var imgView = view.findViewById<ImageView>(R.id.img)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
           mainActivity.showMovie(adapterPosition)
           // onClickHandler(adapterPosition)
          /*  val intentToMoviePager = Intent(view!!.context, MoviePagerActivity::class.java)
            view.context.startActivity(intentToMoviePager)*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val newRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_movie_card_view, parent, false)
        return ListItemHolder(newRow)
    }


    override fun getItemCount(): Int {
        if (movieList != null){
            return movieList.size
        }
        return -1
    }



    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        val movie = movieList[position]
        holder.title.text = movie.title
        holder.imgView.setImageURI(movie.image)

        holder.listNumber.text = movie.listNumber.toString()
        when{
            movie.action ->holder.category.text = mainActivity.resources.getString(R.string.action_text)
            movie.horror ->holder.category.text = mainActivity.resources.getString(R.string.horror_text)
            movie.comedy ->holder.category.text = mainActivity.resources.getString(R.string.comedy_text)
            movie.drama ->holder.category.text = mainActivity.resources.getString(R.string.drama_text)
            movie.thriller ->holder.category.text = mainActivity.resources.getString(R.string.thriller_text)
            movie.western ->holder.category.text = mainActivity.resources.getString(R.string.western_text)
        }
    }



}
