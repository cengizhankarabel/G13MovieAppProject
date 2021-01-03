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
import ise308.karabel.cengizhan.movieappproject.MainActivity.Companion.process

private const val TAG = "FragmentDeleteMovie" //for log

class FragmentDeleteMovie: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.slide_delete_movie, container, false)

        //Toast.makeText(activity, "TEST DELETE PAGE.", Toast.LENGTH_SHORT).show()


        val deleteButton = view.findViewById<Button>(R.id.button_delete_layout)



         deleteButton.setOnClickListener {


             MainActivity.movieList.removeAt(process)




                }



        return view

    }
}