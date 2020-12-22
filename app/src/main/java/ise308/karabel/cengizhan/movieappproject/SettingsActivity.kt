package ise308.karabel.cengizhan.movieappproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {




    private var showDividers: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences("My movie List", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers", true)
        val switch1 = findViewById<Switch>(R.id.switch1)
        switch1.isChecked = showDividers

        switch1.setOnCheckedChangeListener { button, isChecked ->
            showDividers = isChecked

        }
        //movie arrayindeki toplam sayıyı ekrana basıyor
        val countView = findViewById<TextView>(R.id.textView_count)
        countView.text = count.toString()




    }




    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("My movie List", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("dividers", showDividers)
        editor.apply()
    }


}

