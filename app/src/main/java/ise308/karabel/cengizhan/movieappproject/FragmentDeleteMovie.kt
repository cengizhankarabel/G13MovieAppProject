package ise308.karabel.cengizhan.movieappproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

private const val TAG = "FragmentDeleteMovie" //for log
private var movieList: ArrayList<Movie>? = null
private var jsonSerializer : JSONSerializer? =null

class FragmentDeleteMovie: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.slide_delete_movie, container, false)

        //Toast.makeText(activity, "TEST DELETE PAGE.", Toast.LENGTH_SHORT).show()

        val findNumber = view.findViewById<View>(R.id.list_number_layout) as TextView
        val deleteButton = view.findViewById<Button>(R.id.button_delete_layout)

        jsonSerializer = context?.let { JSONSerializer("MyMovieList",  it.applicationContext) }

        try {
            movieList = jsonSerializer!!.load()
        } catch (e: Exception) {
            movieList = ArrayList()
            Log.e(TAG, "Error loading movie information...")
        }



        deleteButton.setOnClickListener {




            for(i in movieList!!)
            {
                if (i.listNumber.toString() == findNumber.text.toString()) {

                    Toast.makeText(activity, "TEST SILME ISLEMI BASARILI ${i.title}  ${findNumber.text}", Toast.LENGTH_SHORT).show()

                    i.title = "Cengizhan"
                    findNumber.text =" "

                    val callActivity = activity as MainActivity
                    callActivity.saveMovie()


                    break

                }
                else{
                    Toast.makeText(activity, "TEST SILME ISLEMI BASARISIZ  ${i.title}", android.widget.Toast.LENGTH_SHORT).show()
                }

            }
        }
        return view

    }
}