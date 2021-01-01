package ise308.karabel.cengizhan.movieappproject

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import kotlin.concurrent.timer

private const val TAG = "FragmentNewMovie" //for log
class FragmentNewMovie : Fragment() {

    private lateinit var imgView: ImageView
    private var imageUri: Uri? = null
    private val imagePickCode = 100


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {




        val view = inflater.inflate(R.layout.slide_add_movie, container, false)


        val editTitle = view.findViewById<EditText>(R.id.edit_title)
        val editYear = view.findViewById<EditText>(R.id.edit_year)
        val editDescription = view.findViewById<EditText>(R.id.edit_description)
        val checkBoxAction =  view.findViewById<CheckBox>(R.id.checkBox_action)
        val checkBoxHorror = view.findViewById<CheckBox>(R.id.checkBox_horror)
        val checkBoxComedy = view.findViewById<CheckBox>(R.id.checkBox_comedy)
        val checkBoxThriller = view.findViewById<CheckBox>(R.id.checkBox_thriller)
        val checkBoxDrama = view.findViewById<CheckBox>(R.id.checkBox_drama)
        val checkBoxWestern = view.findViewById<CheckBox>(R.id.checkBox_western)
        val buttonOk = view.findViewById<Button>(R.id.button_ok)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel)

        imgView = view.findViewById(R.id.imageViewAdd) as ImageView



        buttonCancel.setOnClickListener {

            val callActivity = activity as MainActivity
            callActivity.hideFragment()

            //Toast.makeText(activity, "TEST CANCEL BUTTON.", Toast.LENGTH_SHORT).show()
        }

        imgView.setOnClickListener(){

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, imagePickCode)


        }

        buttonOk.setOnClickListener {

            count +=1


            val newMovie = Movie()

            newMovie.title = editTitle.text.toString()
            newMovie.year = editYear.text.toString()
            newMovie.description = editDescription.text.toString()
            newMovie.action = checkBoxAction.isChecked
            newMovie.horror = checkBoxHorror.isChecked
            newMovie.comedy = checkBoxComedy.isChecked
            newMovie.drama = checkBoxDrama.isChecked
            newMovie.thriller = checkBoxThriller.isChecked
            newMovie.western = checkBoxWestern.isChecked
            newMovie.id += count
            newMovie.listNumber = count
            newMovie.image = imageUri


            val callActivity = activity as MainActivity
            callActivity.createNewMovie(newMovie)
            callActivity.saveMovie()
            callActivity.hideFragment()


            //Toast.makeText(activity, "TEST OK BUTTON.", Toast.LENGTH_SHORT).show()
        }

        return view

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == imagePickCode) {

            //view?.findViewById<ImageView>(R.id.imageView)?.setImageURI(data?.data)
            imageUri = data?.data
            imageUri.toString()
            imgView.setImageURI(imageUri)

        }
    }



}

