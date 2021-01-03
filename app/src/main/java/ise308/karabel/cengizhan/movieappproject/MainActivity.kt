package ise308.karabel.cengizhan.movieappproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {
    private lateinit var animFadeIn: Animation
    companion object{

        var process = 0/*NEW*/
        var processUpdate = 0 /*Update*/
        /*process = 1 "UPDATE" */
        var movieList = ArrayList<Movie>()
    }




    private lateinit var drawerLayout : DrawerLayout


    private var jsonSerializer: JSONSerializer? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: MovieAdapter? = null
    private var showDividers: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animFadeIn= AnimationUtils.loadAnimation(this,R.anim.fade_in)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawerLayoutMenu)


        val navigationView = findViewById<NavigationView>(R.id.NavigationViewMenu)
        navigationView.setNavigationItemSelectedListener(this)

        val actionBarDrawer = ActionBarDrawerToggle(
                this,
                drawerLayout, toolbar,

                R.string.drawer_movie_open,
                R.string.drawer_movie_close)

        drawerLayout.addDrawerListener(actionBarDrawer)
        actionBarDrawer.syncState()



        jsonSerializer = JSONSerializer("MyMovieList", applicationContext)

        try {
            movieList = jsonSerializer!!.load()
            //Toast.makeText(this, "TEST LIST LOADING", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            movieList = ArrayList()
            Log.e(TAG, "Error loading movie information...")
        }




        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter = MovieAdapter(this, movieList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView!!.adapter = adapter




        //  initializeMovie()


    }//override onCreate bitis"


    /* private fun initializeMovie(){

         movieList = ArrayList<Movie>()
         movieList!!.add(Movie(100001,1,"Inception","2010","use of dream-sharing technology",true,false,false,false,false,false))
         movieList!!.add(Movie(100002,2,"Forrest Gump","1994","American romantic comedy-drama filmy",false,false,false,true,false,false))
         movieList!!.add(Movie(100003,3,"Green Street Hooligans","2010","football hooliganism",false,false,false,true,false,false))
         movieList!!.add(Movie(100004,4,"The Godfather","1972","Marlon Brando, Al Pacino, James Caan ",false,false,false,true,false,false))
         movieList!!.add(Movie(100005,5," The Green Mile","1999","child murder and rape",false,false,false,false,true,false))
         movieList!!.add(Movie(100006,6,"Titanic","1997","Leonardo DiCaprio, Kate Winslet, Billy Zane",false,false,false,true,false,false))

     }*/






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



    //process 1 demek yeni bir tane movie oluşturmak demek.
    //bu nedenle add fonksiyonu çalışıyor
    //eğer process 1 değilse remove ve add çalışıyor aynı filmi tekrar yapmasın
    //sadece değiştirsin diye. processin değeri
    fun createNewMovie(movie: Movie) {

        if(process == 0){
            Toast.makeText(this, "Add Islemi Yapıldı proces = $process.", Toast.LENGTH_SHORT).show()
            movieList.add(movie)
            saveMovie()
        }else{
            Toast.makeText(this, "Update Islemi Yapıldı proces = $process.", Toast.LENGTH_SHORT).show()
            movieList.removeAt(processUpdate)
            movieList.add(processUpdate,movie)
            saveMovie()
            process=0
            Toast.makeText(this, "Update Isleminden sonra  proces = $process.", Toast.LENGTH_SHORT).show()


        }
        adapter!!.notifyDataSetChanged()

    }

    fun showMovie(movieToShow: Int) {
        val dialog = FragmentShowMovie()
        processUpdate = movieToShow
        movieList[movieToShow].let { dialog.sendMovieSelected(it) }

        findViewById<RecyclerView>(R.id.recyclerView).visibility = View.GONE

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentSlides, FragmentShowMovie()).setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
        fragmentTransaction.commit()
    }




    fun saveMovie() {
        Toast.makeText(this, "TEST SAVE FUNCTION.", Toast.LENGTH_SHORT).show()
        try {
            jsonSerializer!!.save(movieList)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading notes...")
        }
    }

    public override fun onPause() {
        super.onPause()
        //Toast.makeText(this, "Group 13 working all the time", Toast.LENGTH_SHORT).show()
    }



    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)

        } else {
            super.onBackPressed()
        }

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.list_movies){

            findViewById<RecyclerView>(R.id.recyclerView).visibility = View.VISIBLE }

        if(item.itemId == R.id.add_movie){
            supportFragmentManager.beginTransaction().replace(
                    R.id.fragmentSlides, FragmentNewMovie()).commit()
            findViewById<RecyclerView>(R.id.recyclerView).visibility = View.GONE }


        drawerLayout.closeDrawer((GravityCompat.START))
        return  true
    }




    fun hideFragment() {


        findViewById<RecyclerView>(R.id.recyclerView).visibility = View.VISIBLE

    }






}