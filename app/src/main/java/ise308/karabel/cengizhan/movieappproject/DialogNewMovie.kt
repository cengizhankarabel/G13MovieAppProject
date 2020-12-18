package ise308.karabel.cengizhan.movieappproject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment


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






        if (count == 0) {
            builder.setView(dialogLayout).setMessage(resources.getString(R.string.add_first_movie))

        } else {
            builder.setView(dialogLayout).setMessage(resources.getString(R.string.add_new_movie))
        }





        buttonCancel.setOnClickListener {
            dismiss()
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



    }



