package ise308.karabel.cengizhan.movieappproject

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment
import kotlin.*


class DialogNewMovie : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {



        val builder = AlertDialog.Builder(activity!!)


        val inflater = activity!!.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_new_movie, null)

        val editTitle = dialogLayout.findViewById<EditText>(R.id.edit_title)
        val editYear = dialogLayout.findViewById<EditText>(R.id.edit_year)
        val editDescription = dialogLayout.findViewById<EditText>(R.id.edit_description)
        val checkBoxAction = dialogLayout.findViewById<CheckBox>(R.id.checkBox_action)
        val checkBoxHorror = dialogLayout.findViewById<CheckBox>(R.id.checkBox_horror)
        val checkBoxComedy = dialogLayout.findViewById<CheckBox>(R.id.checkBox_comedy)
        val checkBoxThriller = dialogLayout.findViewById<CheckBox>(R.id.checkBox_thriller)
        val checkBoxDrama = dialogLayout.findViewById<CheckBox>(R.id.checkBox_drama)
        val checkBoxWestern = dialogLayout.findViewById<CheckBox>(R.id.checkBox_western)
        val buttonOk = dialogLayout.findViewById<Button>(R.id.button_ok)
        val buttonCancel = dialogLayout.findViewById<Button>(R.id.button_cancel)

        val imageView = dialogLayout.findViewById<ImageView>(R.id.imageView)






        if (count == 0) {
            builder.setView(dialogLayout).setMessage(resources.getString(R.string.add_first_movie))

        } else {
            builder.setView(dialogLayout).setMessage(resources.getString(R.string.add_new_movie))
        }





        buttonCancel.setOnClickListener {
            dismiss()
        }






        imageView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    pickImageFromGallery();
                }
            } else {
                pickImageFromGallery();
            }
        }



        buttonOk.setOnClickListener {
            val newMovie = Movie()
            newMovie.title = editTitle.text.toString()
            newMovie.year = editYear.text.toString()
            newMovie.description = editDescription.text.toString()
            newMovie.action = checkBoxAction.isChecked
            newMovie.horror = checkBoxHorror.isChecked
            newMovie.comedy = checkBoxComedy.isChecked
            newMovie.drama = checkBoxThriller.isChecked
            newMovie.thriller = checkBoxDrama.isChecked
            newMovie.western = checkBoxWestern.isChecked


            count += 1


            val callingActivity = activity as MainActivity?
            callingActivity!!.createNewMovie(newMovie)

            dismiss()

        }








        return builder.create()


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
    /*override fun onActivitycResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data?.data)

        }
    }*/






}


