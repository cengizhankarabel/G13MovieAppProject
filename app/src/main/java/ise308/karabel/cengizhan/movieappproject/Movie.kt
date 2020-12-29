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
private val JSON_ID = "id"
private val JSON_LIST = "list"



var count: Int = 0
class Movie {


    var id: Int = 100000
    var listNumber: Int = 1
    var title: String? = null
    var description: String? = null
    var year: String? = null
    var action: Boolean = false
    var horror: Boolean = false
    var comedy: Boolean = false
    var drama: Boolean = false
    var thriller: Boolean = false
    var western: Boolean = false




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

        id = jsonObject.getInt(JSON_ID)
        listNumber = jsonObject.getInt(JSON_LIST)
        count = jsonObject.getInt(JSON_COUNT)




    }


    constructor(){

    }

    constructor( id: Int, listNumber: Int, title: String, year: String, description: String,
        action: Boolean, horror: Boolean, comedy: Boolean, drama: Boolean, thriller: Boolean, western: Boolean)
    {
        this.id = id
        this.listNumber =listNumber
        this.title=title
        this.year = year
        this.description = description
        this.action = action
        this.horror = horror
        this.comedy = comedy
        this.drama = drama
        this.thriller = thriller
        this.western = western
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
        jsonObject.put(JSON_ID,id)
        jsonObject.put(JSON_LIST,listNumber)




        return jsonObject

    }


}