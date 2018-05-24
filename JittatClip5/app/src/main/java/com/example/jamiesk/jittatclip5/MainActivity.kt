package com.example.jamiesk.jittatclip5

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    val items = ArrayList<ExchangePair>()

    lateinit var adapter: ExchangePairAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add(ExchangePair("AAA", "BBB", 0.001 ))

        val layoutManager = LinearLayoutManager(this )
        exchangeView.layoutManager = layoutManager

        adapter = ExchangePairAdapter(items)
        exchangeView.adapter = adapter
    }

    fun refreshButtonClicked() {
        // mainTextView.text = "Loading..."

        val loaderTask = BxLoaderTask()
        loaderTask.execute()
    }

    fun updateBxJson(json: String) {
        val obj = JSONObject(json)
        val builder = StringBuilder()

        items.clear()

        for(key in obj.keys()) {
            val expairObj = obj.getJSONObject(key)

            val expair = ExchangePair( expairObj.getString("primary_currency"),
                    expairObj.getString("secondary_currency"),
                    expairObj.getDouble("last_price"))
            items.add(expair)
        }
        adapter.notifyDataSetChanged()
        // mainTextView.text = builder.toString()
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
                if( result != "" ) {
                    updateBxJson( result )
                }
            }
        }

    }
}
