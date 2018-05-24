package com.example.jamiesk.jittatclip2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val items = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add( "Hello" )
        items.add( "World" )

        adapter = ArrayAdapter<String>(this ,
                android.R.layout.simple_list_item_1 ,
                items )

        itemListView.adapter = adapter
    }

    fun saveButtonclicked( view: View ) {
       val msg = itemEditText.text.toString()
        Toast.makeText( this, msg, Toast.LENGTH_LONG ).show()
        items.add( msg )
        adapter?.notifyDataSetChanged()
        itemEditText.text.clear()
    }

}
