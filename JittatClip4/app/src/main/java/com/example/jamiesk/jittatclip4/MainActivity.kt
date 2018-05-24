package com.example.jamiesk.jittatclip4

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun refreshButtonClicked() {
        mainTextView.text = "Loading..."

        val loaderTask = BxLoaderTask()
        loaderTask.execute()
    }

    fun updateBxJson(json: String) {
        val obj = JSONObject(json)
        val builder = StringBuilder()

        for(key in obj.keys()) {
            val expair = obj.getJSONObject(key)
            builder.append(expair.getString("primary_currency") +
                    "/" +
            expair.getString( "secondary_currency" ))
            builder.append("\n")
        }
        mainTextView.text = builder.toString()
    }

    inner class BxLoaderTask: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg p0: String?): String {
            val url = URL("https://bx.in.th/api/")
            try {
                val json = url.readText()
                return json
            } catch(ioe: IOException) {
                return ""
            }
        }

        override fun onPostExecute(result: String?) {
            if(result != null) {
                updateBxJson( result )
            }
        }

    }
}
