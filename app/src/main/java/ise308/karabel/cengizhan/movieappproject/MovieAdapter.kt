package ise308.karabel.cengizhan.movieappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val mainActivity: MainActivity, private val moveList: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ListItemHolder>(){

        inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {


            internal var title = view.findViewById<View>(R.id.textView_movie_title) as TextView
            internal var year = view.findViewById<View>(R.id.textView_movie_year) as TextView//su an pasif durumda*cengizhan*
            internal var description =
                view.findViewById<View>(R.id.textView_movie_description) as TextView
            internal var categorie =
                view.findViewById<View>(R.id.textView_movie_categorie) as TextView

            init {
                view.isClickable = true
                view.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                mainActivity.showMovie(adapterPosition)
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
        val substringLength = if(movie.description!!.length <15) movie.description!!.length else 15
        val shortDesc = "${movie.description!!.substring(0, substringLength)}..."
        holder.description.text = shortDesc
        when {
            movie.action ->holder.categorie.text = "Action"
        }
    }


    override fun getItemCount(): Int {
        if (moveList != null){
            return moveList.size
        }
        return -1
    }



        }