package com.example.jamiesk.jittatclip6

import com.example.jamiesk.jittatclip6.presenters.CounterPresenter
import com.example.jamiesk.jittatclip6.presenters.CounterView
import org.junit.Before
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by JamieSK on 23/5/2018 AD.
 */
class CounterPresenterTest {

    lateinit var counterView: CounterView
    lateinit var presenter: CounterPresenter

    @Before
    fun beforeEachTest() {
        val counterView = mock(CounterView::class.java)
        val presenter = CounterPresenter(counterView)
    }

    @Test
    fun testStart() {
        presenter.start()
        verify(counterView).setCounter(0)
    }

    @Test
    fun testIncFromStart() {
        presenter.start()
        presenter.onIncButtonClicked()
        verify(counterView).setCounter(1)
        verify(counterView).setCounter(0)
    }

}