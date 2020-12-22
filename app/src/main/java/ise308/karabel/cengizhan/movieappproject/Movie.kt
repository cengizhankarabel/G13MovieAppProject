package ise308.karabel.cengizhan.movieappproject

import android.app.AlertDialog
import android.app.Dialog
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


private val JSON_TITLE = "title"
private val JSON_YEAR = "year"
private val JSON_DESCRIPTION = "description"
private val JSON_ACTION = "action"
private val JSON_HORROR = "horror"
private val JSON_COMEDY = "comedy"
private val JSON_DRAMA = "drama"
private val JSON_THRILLER = "thriller"
private val JSON_WESTERN = "western"
private val JSON_COUNT = "count"
private val JSON_IMAGE = "image"



var count: Int = 0  //arraydeki film sayısını tutmak icin
class Movie {


    var title: String? = null
    var description: String? = null
    var year: String? = null
    var action: Boolean = false
    var horror: Boolean = false
    var comedy: Boolean = false
    var drama: Boolean = false
    var thriller: Boolean = false
    var western: Boolean = false
    lateinit var imageOfMovie : ImageView  //bunu öğrenmemiz gerekiyor





    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject){
        title = jsonObject.getString(JSON_TITLE)
        year = jsonObject.getString(JSON_YEAR)
        description = jsonObject.getString(JSON_DESCRIPTION)
        action = jsonObject.getBoolean(JSON_ACTION)
        horror = jsonObject.getBoolean(JSON_HORROR)
        comedy = jsonObject.getBoolean(JSON_COMEDY)
        drama = jsonObject.getBoolean(JSON_DRAMA)
        thriller = jsonObject.getBoolean(JSON_THRILLER)
        western = jsonObject.getBoolean(JSON_WESTERN)




        jsonObject.put(JSON_COUNT,count)
        count+=1

    }

    constructor(){

    }

    @Throws(JSONException::class)
    fun convertTOJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(JSON_TITLE, title)
        jsonObject.put(JSON_YEAR, year)
        jsonObject.put(JSON_DESCRIPTION, description)
        jsonObject.put(JSON_ACTION, action)
        jsonObject.put(JSON_HORROR, horror)
        jsonObject.put(JSON_COMEDY, comedy)
        jsonObject.put(JSON_DRAMA, drama)
        jsonObject.put(JSON_THRILLER, thriller)
        jsonObject.put(JSON_WESTERN, western)


        jsonObject.put(JSON_COUNT,count)



        return jsonObject
    }




}