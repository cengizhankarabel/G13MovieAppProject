package ise308.karabel.cengizhan.movieappproject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class DialogShowMovie : DialogFragment() {

    private var movie: Movie? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {



        val builder = AlertDialog.Builder(this.activity!!)
        val inflater = activity!!.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_show_movie, null)

        val textViewTitle = dialogLayout.findViewById<TextView>(R.id.textView_title)
        val textViewYear = dialogLayout.findViewById<TextView>(R.id.textView_year)
        val textViewDescription = dialogLayout.findViewById<TextView>(R.id.textView_description)
        //kendime not : tamammlamayı unutma //*cengizhan*
        val textViewAction = dialogLayout.findViewById<TextView>(R.id.textView_action)
        val textViewHorror = dialogLayout.findViewById<TextView>(R.id.checkBox_horror)
        val textViewComedy = dialogLayout.findViewById<TextView>(R.id.checkBox_comedy)
        val textViewThriller = dialogLayout.findViewById<TextView>(R.id.checkBox_thriller)
        val textViewDrama = dialogLayout.findViewById<TextView>(R.id.checkBox_drama)
        val textViewWestern = dialogLayout.findViewById<TextView>(R.id.checkBox_western)
        val buttonDone = dialogLayout.findViewById<Button>(R.id.button_done)




        textViewTitle.text = movie!!.title
        textViewYear.text = movie!!.year
        textViewDescription.text = movie!!.description




        if(!movie!!.action){

            textViewAction.visibility = View.GONE
        }


        buttonDone.setOnClickListener {
            dismiss()
        }

        builder.setView(dialogLayout)
                .setMessage("Information of Movie")



        return builder.create()
    }





    fun sendMovieSelected(moveselected: Movie){
        movie = moveselected
    }
}