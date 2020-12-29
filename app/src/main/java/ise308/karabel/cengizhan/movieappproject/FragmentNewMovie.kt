package ise308.karabel.cengizhan.movieappproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

private const val TAG = "FragmentNewMovie" //for log
class FragmentNewMovie : Fragment() {
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

        val imgView = view.findViewById<ImageView>(R.id.imageView)



        buttonCancel.setOnClickListener {

            val callActivity = activity as MainActivity
            callActivity.hideFragment()


            //Toast.makeText(activity, "TEST CANCEL BUTTON.", Toast.LENGTH_SHORT).show()
        }

        imgView.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                pickImageFromGallery();
            }
        }

        /* override fun onActivitycResult(requestCode: Int, resultCode: Int, data: Intent?) {
             if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
                 imgView.setImageURI(data?.data)

             }
         }*/





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



            val callActivity = activity as MainActivity
            callActivity.createNewMovie(newMovie)
            callActivity.saveMovie()
            callActivity.hideFragment()


            //Toast.makeText(activity, "TEST OK BUTTON.", Toast.LENGTH_SHORT).show()
        }

        return view

    }




    private fun checkSelfPermission(externalStorage: String): Int {
        return -1
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }



}

