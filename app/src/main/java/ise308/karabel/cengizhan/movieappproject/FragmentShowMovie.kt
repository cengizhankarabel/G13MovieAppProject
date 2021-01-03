package ise308.karabel.cengizhan.movieappproject

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.system.exitProcess

private const val TAG = "FragmentShowMovie" //for log
class FragmentShowMovie  : Fragment() {
    private lateinit var animFadeIn: Animation

    private var movie: Movie? = null

    /*companion object{

        val selectMovie = MainActivity.movieList[MainActivity.process]
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Toast.makeText(activity, "proces processUpdate = ${MainActivity.processUpdate}.", Toast.LENGTH_SHORT).show()

        val view = inflater.inflate(R.layout.show_movie, container, false)

        animFadeIn= AnimationUtils.loadAnimation(activity,R.anim.fade_in)
        animFadeIn.duration=3000
        //view.findViewById<Button>(R.id.button_delete).setOnClickListener


        val selectMovie = MainActivity.movieList[MainActivity.processUpdate]



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

        val deleteAnim= view.findViewById<TextView>(R.id.deletedanim)

       textViewTitle.text = selectMovie.title
       textViewYear.text = selectMovie.year
       textViewDescription.text = selectMovie.description




        /*     textViewYear.text = movie!!.year
          textViewDescription.text = movie!!.description
          //imgView.setImageURI(imageUri) = arguments!!.get("image")*/
        textViewAction.visibility = View.GONE
        textViewHorror.visibility = View.GONE
        textViewComedy.visibility = View.GONE
        textViewThriller.visibility = View.GONE
        textViewDrama.visibility = View.GONE
        textViewWestern.visibility = View.GONE

          if(selectMovie.action){
              textViewAction.visibility = View.VISIBLE
          }

          if(selectMovie.horror){
              textViewHorror.visibility = View.VISIBLE
          }

          if(selectMovie.comedy){

              textViewComedy.visibility = View.VISIBLE

          }

          if(selectMovie.thriller){

              textViewThriller.visibility = View.VISIBLE

          }

          if(selectMovie.drama){

              textViewDrama.visibility = View.VISIBLE

          }

          if(selectMovie.western){

              textViewWestern.visibility = View.VISIBLE
          }





        buttonEdit.setOnClickListener {

            Toast.makeText(activity, "Edit Function ${MainActivity.process}.", Toast.LENGTH_SHORT).show()
            val callActivity = activity as MainActivity
            MainActivity.process =1
            Toast.makeText(activity, "Edit Function ${MainActivity.process}.", Toast.LENGTH_SHORT).show()

            callActivity.supportFragmentManager.beginTransaction().replace(R.id.fragmentSlides, FragmentUpdateMovie()).commit()

            deleteAnim.startAnimation(animFadeIn)


        }


        buttonDelete.setOnClickListener {
            count -=1
            MainActivity.movieList.removeAt(MainActivity.processUpdate)
            val callingActivity = activity as MainActivity
            callingActivity.saveMovie()
            callingActivity.hideFragment()



           /* val callActivity = activity as MainActivity
            callActivity.supportFragmentManager.beginTransaction().replace(R.id.fragmentSlides, FragmentDeleteMovie()).commit()
*/
        }









        return view
    }


       /* companion object{
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
            // bundle.("image",movie.image)


            fragment.arguments = bundle
            return fragment

        }
    }*/




    fun sendMovieSelected(noteselected: Movie){
        movie = noteselected
    }

}
