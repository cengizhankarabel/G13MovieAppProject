package ise308.karabel.cengizhan.movieappproject
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ShowMovieFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_show_movie, container, false)

        val textViewTitle = view.findViewById<TextView>(R.id.textView_title)
        val textViewYear = view.findViewById<TextView>(R.id.textView_year)
        val textViewDescription = view.findViewById<TextView>(R.id.textView_description)
        val textViewAction = view.findViewById<TextView>(R.id.textView_action)
        //val textViewHorror = view.findViewById<TextView>(R.id.checkBox_horror)
        // val textViewComedy = view.findViewById<TextView>(R.id.checkBox_comedy)
        // val textViewThriller = view.findViewById<TextView>(R.id.checkBox_thriller)
        //val textViewDrama = view.findViewById<TextView>(R.id.checkBox_drama)
        //  val textViewWestern = view.findViewById<TextView>(R.id.checkBox_western)
        //  val buttonDone = view.findViewById<Button>(R.id.button_done)
        // val buttonEdit = view.findViewById<Button>(R.id.button_edit)
        //   val buttonDelete = view.findViewById<Button>(R.id.button_delete)

        textViewTitle.text = arguments!!.getString("Title")
        textViewYear.text = arguments!!.getString("Year")
        textViewDescription.text = arguments!!.getString("Description")

        if(!arguments!!.getBoolean("Action")) textViewAction.visibility = View.GONE
        // if(!arguments!!.getBoolean("Horror")) textViewHorror.visibility = View.GONE
        //  if(!arguments!!.getBoolean("Comedy")) textViewComedy.visibility = View.GONE
        //  if(!arguments!!.getBoolean("Thriller")) textViewThriller.visibility = View.GONE
        //  if(!arguments!!.getBoolean("Drama")) textViewDrama.visibility = View.GONE
        // if(!arguments!!.getBoolean("Delete")) textViewWestern.visibility = View.GONE



        return view
    }

    companion object{
        fun newInstance(movie: Movie) : ShowMovieFragment{

            val fragment = ShowMovieFragment()
            val bundle = Bundle(1)
            bundle.putString("title", movie.title)
            bundle.putString("year",movie.year)
            bundle.putString("Description",movie.description)
            bundle.putBoolean("Action",movie.action)
            bundle.putBoolean("Horror",movie.horror)
            bundle.putBoolean("Comedy",movie.comedy)
            bundle.putBoolean("Thriller",movie.thriller)
            bundle.putBoolean("Drama",movie.drama)
            bundle.putBoolean("Western",movie.western)
            fragment.arguments = bundle
            return fragment

        }
    }
}