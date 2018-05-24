package com.example.jamiesk.jittatclip6

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jamiesk.jittatclip6.presenters.CounterPresenter
import com.example.jamiesk.jittatclip6.presenters.CounterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CounterView {

    lateinit var presenter: CounterPresenter

    override fun setCounter(value: Int) {
        counterTextView.text = value.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = CounterPresenter( this )

        presenter.start()
    }

    fun onIncButtonClicked(view: View) {
        presenter.onIncButtonClicked()
    }

    fun onDecButtonClicked(view: View) {
        presenter.onDecButtonClicked()
    }

    fun onResetButtonClicked(view: View) {
        presenter.onResetButtonClicked()
    }

}
