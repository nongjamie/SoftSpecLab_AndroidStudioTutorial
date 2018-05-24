package com.example.jamiesk.jittatclip3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    final val TASK_NAME_REQUEST_CODE = 100

    final val PREF_NAME = "task_list"
    final val TASK_LIST_PREF_KEY = "items"

    val items = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add("Test")

        adapter = ArrayAdapter( this,
                android.R.layout.simple_list_item_1,
                items )
        taskListView.adapter = adapter

        restoreTaskList()
    }

    private fun restoreTaskList() {
        val preference = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val data = preference.getString(TASK_LIST_PREF_KEY, null)
        if(data != null) {
            for(st in data.split(",")) {
                if(st != "") {
                    items.add(st)
                }
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onStop() {
        super.onStop()

        saveTaskList()
    }

    private fun saveTaskList() {
        val builder = StringBuilder()
        for(st in items) {
            builder.append(st)
            builder.append(",")
        }
        val data = builder.toString()

        val preference = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString(TASK_LIST_PREF_KEY, data)
        editor.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode == TASK_NAME_REQUEST_CODE ) {
            if( resultCode == Activity.RESULT_OK ) {
                if( data != null ) {
                    val taskName = data.getStringExtra( "TASK_NAME_KEY" )
                    if( taskName != null ) {
                        items.add( taskName )
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun newButtonClicked(view: View) {
        val intent = Intent(this, InputActivity::class.java )
        startActivityForResult( intent, TASK_NAME_REQUEST_CODE )
    }
}
