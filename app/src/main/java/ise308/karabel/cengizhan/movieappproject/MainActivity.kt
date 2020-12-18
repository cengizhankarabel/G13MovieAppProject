package ise308.karabel.cengizhan.movieappproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.jar.Manifest

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private var movieList: ArrayList<Movie>? = null
    private var jsonsSerializer: JSONSerializer? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: MovieAdapter? = null
    private var showDividers: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabNewMovie = findViewById<FloatingActionButton>(R.id.fab_new_movie)
        fabNewMovie.setOnClickListener {
            val dialogNewMovie = DialogNewMovie()
            dialogNewMovie.show(supportFragmentManager, "124")
        }

        jsonsSerializer = JSONSerializer("MyMovieList", applicationContext)

        try {
            movieList = jsonsSerializer!!.load()
        } catch (e: Exception) {
            movieList = ArrayList()
            Log.e(TAG, "Error loading movie information...")
        }

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter = MovieAdapter(this, movieList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView!!.adapter = adapter

    }



        override fun onResume() {
            super.onResume()
            val prefs = getSharedPreferences("My movie List", Context.MODE_PRIVATE)
            showDividers = prefs.getBoolean("dividers", true)
            if (showDividers) {
                recyclerView!!.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                    )
                )
            } else {
                if (recyclerView!!.itemDecorationCount > 0)
                    recyclerView!!.removeItemDecorationAt(0)
            }
        }

        fun createNewMovie(movie: Movie) {
            movieList!!.add(movie)
            adapter!!.notifyDataSetChanged()
        }

        fun showMovie(movieToShow: Int) {
            val dialog = DialogShowMovie()
            movieList?.get(movieToShow)?.let { dialog.sendMovieSelected(it) }
            dialog.show(supportFragmentManager, "")
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
        }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            val b = when (id) {
                R.id.settings -> {
                    val intentToSettings = Intent(this, SettingsActivity::class.java)
                    startActivity(intentToSettings)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
            return b
        }

        private fun saveMovie() {
            try {
                jsonsSerializer!!.save(this.movieList!!)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading notes...")
            }
        }

        override fun onPause() {
            super.onPause()
            saveMovie()
        }



    }




