package com.example.jamiesk.jittatclip6.presenters

import android.view.View

/**
 * Created by JamieSK on 23/5/2018 AD.
 */
class CounterPresenter(val counterView: CounterView) {

    private var counter: Int = 0

    fun onIncButtonClicked() {
        counter++
        updateCounter()
    }

    fun onDecButtonClicked() {
        counter--
        updateCounter()
    }

    fun onResetButtonClicked() {
        reset()
    }

    fun start() {
        reset()
    }

    private fun reset() {
        counter = 0
        updateCounter()
    }

    private fun updateCounter() {
        counterView.setCounter(counter)
    }

}