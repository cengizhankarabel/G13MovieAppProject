package ise308.karabel.cengizhan.movieappproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import ise308.karabel.cengizhan.movieappproject.JSONSerializer

private const val TAG = "MoviePagerActivity" //for log
private var movieList: ArrayList<Movie>? = null
private var jsonSerializer : JSONSerializer? =null


class MoviePagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_pager)

        jsonSerializer = JSONSerializer("MyMovieList", applicationContext)

        try {
            movieList = jsonSerializer!!.load()
        } catch (e: Exception) {
            movieList = ArrayList()
            Log.e(TAG, "Error loading movie information...")
        }

        var movieFragmentList = java.util.ArrayList<Fragment>()
        for(movie in movieList!!){
            movieFragmentList.add(ShowMovieFragment.newInstance(movie))
        }

        val moviePageAdapter = MoviePagerAdapter(supportFragmentManager, movieFragmentList)
        findViewById<ViewPager>(R.id.pager_movie).adapter = moviePageAdapter
    }

    class MoviePagerAdapter(fm: FragmentManager, private val movieFragmentList: ArrayList<Fragment>)
        : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        override fun getCount() = movieFragmentList.size


        override fun getItem(position: Int) = movieFragmentList[position]

    }

}
