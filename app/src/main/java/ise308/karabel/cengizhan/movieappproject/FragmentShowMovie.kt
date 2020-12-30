package ise308.karabel.cengizhan.movieappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

private const val TAG = "FragmentShowMovie" //for log
class FragmentShowMovie  : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.show_movie, container, false)

        val textViewTitle = view.findViewById<TextView>(R.id.textView_title)
        val textViewYear = view.findViewById<TextView>(R.id.textView_year)
        val textViewDescription = view.findViewById<TextView>(R.id.textView_description)
        val textViewAction = view.findViewById<TextView>(R.id.textView_action)
        val textViewHorror = view.findViewById<TextView>(R.id.textView_horror)
        val textViewComedy = view.findViewById<TextView>(R.id.textView_comedy)
        val textViewThriller = view.findViewById<TextView>(R.id.textView_thriller)
        val textViewDrama = view.findViewById<TextView>(R.id.textView_drama)
        val textViewWestern = view.findViewById<TextView>(R.id.textView_western)
        val buttonEdit = view.findViewById<Button>(R.id.button_edit)
        val buttonDelete = view.findViewById<Button>(R.id.button_delete)



        buttonEdit.setOnClickListener {
            //FragmentManager manager = getActivity().getSupportFragmentManager();

            val callActivity = activity as MainActivity
            callActivity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.enter_right_to_left).replace(R.id.fragmentSlides, FragmentUpdateMovie()).commit()
        }


        buttonDelete.setOnClickListener {

            val callActivity = activity as MainActivity
            callActivity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.enter_right_to_left).replace(R.id.fragmentSlides, FragmentDeleteMovie()).commit()

        }


        //   val layoutCont = view.findViewById<ImageView>(R.id.imageView)
        //  val anaekran = view.findViewById<ImageView>(R.id.imageView3)
        textViewTitle.text = arguments!!.getString("Title")
        textViewYear.text = arguments!!.getString("Year")
        textViewDescription.text = arguments!!.getString("Description")


        // if(!arguments!!("Image")) layoutCont.visibility = View.GONE
        if(!arguments!!.getBoolean("Action")) textViewAction.visibility = View.GONE
        if(!arguments!!.getBoolean("Horror")) textViewHorror.visibility = View.GONE
        if(!arguments!!.getBoolean("Comedy")) textViewComedy.visibility = View.GONE
        if(!arguments!!.getBoolean("Thriller")) textViewThriller.visibility = View.GONE
        if(!arguments!!.getBoolean("Drama")) textViewDrama.visibility = View.GONE
        if(!arguments!!.getBoolean("Western")) textViewWestern.visibility = View.GONE



        return view
    }

    companion object{
        fun newInstance(movie: Movie) : FragmentShowMovie{

            val fragment = FragmentShowMovie()
            val bundle = Bundle(1)
            bundle.putString("Title", movie.title)
            bundle.putString("Year",movie.year)
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
