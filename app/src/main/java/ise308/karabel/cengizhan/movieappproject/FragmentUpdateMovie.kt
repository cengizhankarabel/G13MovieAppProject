package ise308.karabel.cengizhan.movieappproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.json.JSONObject


private const val TAG = "FragmentUpdateMovie" //for log
private var movieList: ArrayList<Movie>? = null
private var jsonSerializer : JSONSerializer? =null
class FragmentUpdateMovie : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {

        val view = inflater.inflate(R.layout.slide_update_movie,container,false)

        val showBtn = view.findViewById<Button>(R.id.button_show_information)
        var updateTitle = view.findViewById<EditText>(R.id.update_title)
        val updateYear = view.findViewById<EditText>(R.id.update_year)
        val updateDescription =view.findViewById<EditText>(R.id.update_description)
        val updateFindMovie =view.findViewById<EditText>(R.id.update_movie_layout)
        val updateBtn = view.findViewById<Button>(R.id.button_update_information)

        jsonSerializer = context?.let { JSONSerializer("MyMovieList",  it.applicationContext) }

        try {
            movieList = jsonSerializer!!.load()
        } catch (e: Exception) {
            movieList = ArrayList()
            Log.e(TAG, "Error loading movie information...")
        }



        showBtn.setOnClickListener {


            updateFindMovie.visibility = View.GONE
            updateTitle.visibility = View.VISIBLE
            updateYear.visibility = View.VISIBLE
            updateDescription.visibility = View.VISIBLE
            showBtn.visibility = View.GONE
            updateBtn.visibility = View.VISIBLE

        }

        updateBtn.setOnClickListener {

            for(i in movieList!!){

                if(i.title == updateFindMovie.text.toString() ){

                    i.title = updateTitle.text.toString()
                    i.year = updateYear.text.toString()
                    i.description = updateDescription.text.toString()

                    Toast.makeText(activity, "UPDATE ISLEMI BASARILI ${i.title}  ${updateFindMovie.text}", Toast.LENGTH_SHORT).show()


                  //  var js = JSONObject()

                    val callActivity = activity as MainActivity
                    callActivity.createNewMovie(i)
                    callActivity.saveMovie()


                    break
                }
                else{

                    Toast.makeText(activity, "BULUNAMADI ${i.title}  ${updateFindMovie.text}", Toast.LENGTH_SHORT).show()

                }
            }
        }

        return view
    }
}