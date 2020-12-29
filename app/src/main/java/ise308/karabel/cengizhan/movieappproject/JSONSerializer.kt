package ise308.karabel.cengizhan.movieappproject

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*

class JSONSerializer (private val filename : String, private  val context: Context) {

    @Throws(IOException::class, JSONException::class)
    fun save(movieList: List<Movie>){
        val jsonArray = JSONArray()
        for (movie in movieList){
            jsonArray.put(movie.convertTOJSON())
        }
        var writer: Writer? = null
        try {
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        } finally {
            if (writer != null){
                writer.close()
            }
        }

    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()
        var reader : BufferedReader? = null

        try {
            val infile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(infile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()){
                jsonString.append(line)
            }

            val jsonArray = JSONTokener(jsonString.toString())
                    .nextValue() as JSONArray

            for (i in 0 until jsonArray.length()){
                movieList.add(Movie(jsonArray.getJSONObject(i)))
            }


        } catch (e: FileNotFoundException) {

        } finally {
            reader!!.close()
        }
        return movieList
    }

}
