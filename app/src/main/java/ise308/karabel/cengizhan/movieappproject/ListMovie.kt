package ise308.karabel.cengizhan.movieappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ListMovie : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //sayfalar arası geçiş sağlamak için kullandığımız class

        return inflater.inflate(R.layout.activity_movie_pager, container, false)
    }
}