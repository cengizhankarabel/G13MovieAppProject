package ise308.karabel.cengizhan.movieappproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.json.JSONObject


private const val TAG = "FragmentUpdateMovie" //for log
private var movieList: ArrayList<Movie>? = null
private var jsonSerializer : JSONSerializer? =null

class FragmentUpdateMovie : Fragment() {

    private lateinit var animFadeIn: Animation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {


        //  val selectMovie = MainActivity.movieList[MainActivity.process]

        val view = inflater.inflate(R.layout.slide_update_movie, container, false)


        val updateTitle = view.findViewById<EditText>(R.id.update_title)
        val updateYear = view.findViewById<EditText>(R.id.update_year)
        val updateDescription = view.findViewById<EditText>(R.id.update_description)
        val updateBtn = view.findViewById<Button>(R.id.button_update_information)

        animFadeIn= AnimationUtils.loadAnimation(activity,R.anim.fade_in)


   /*   jsonSerializer = context?.let { JSONSerializer("MyMovieList", it.applicationContext) }

        try {
            movieList = jsonSerializer!!.load()
        } catch (e: Exception) {
            movieList = ArrayList()
            Log.e(TAG, "Error loading movie information...")
        }*/
        val selectMovie = MainActivity.movieList[MainActivity.processUpdate]

        if(MainActivity.process != 0) {
            Toast.makeText(activity, "update proces = ${MainActivity.processUpdate}.", Toast.LENGTH_SHORT).show()

            updateTitle.visibility = View.VISIBLE
            updateTitle.setText(selectMovie.title)

            updateYear.visibility = View.VISIBLE
            updateYear.setText(selectMovie.year)

            updateDescription.visibility = View.VISIBLE
            updateDescription.setText(selectMovie.description)

            updateBtn.visibility = View.VISIBLE
        }


        updateBtn.setOnClickListener {
            val newMovie = Movie()
            newMovie.title = updateTitle.text.toString()
            newMovie.year = updateYear.text.toString()
            newMovie.description = updateDescription.text.toString()

            newMovie.action=selectMovie.action
            newMovie.comedy=selectMovie.comedy
            newMovie.drama=selectMovie.drama
            newMovie.horror=selectMovie.horror
            newMovie.thriller=selectMovie.thriller
            newMovie.western=selectMovie.western

           // update  1
            //new  2

            Toast.makeText(activity, "SONNNNN proces = ${MainActivity.process}.", Toast.LENGTH_SHORT).show()

                    val callActivity = activity as MainActivity
                    callActivity.createNewMovie(newMovie)
                    callActivity.hideFragment()
                  //  callActivity.saveMovie()



                }

        return view
    }
}