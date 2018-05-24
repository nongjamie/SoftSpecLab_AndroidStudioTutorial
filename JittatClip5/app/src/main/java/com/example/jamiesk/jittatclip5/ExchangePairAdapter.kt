package com.example.jamiesk.jittatclip5

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.exchange_item.view.*

/**
 * Created by JamieSK on 23/5/2018 AD.
 */
class ExchangePairAdapter(val items: ArrayList<ExchangePair>) : RecyclerView.Adapter<ExchangePairViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ExchangePairViewHolder {
     val v = LayoutInflater.from(parent?.context).inflate(R.layout.exchange_item, parent, false)
        return ExchangePairViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ExchangePairViewHolder?, position: Int) {
        holder?.bind( items[position] )
    }

}

class ExchangePairViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val primaryCurrencyView = itemView.itemPrimaryCurrencyTextView
    val secondaryCurrencyView = itemView.itemSecondaryCurrencyTextView
    val priceView = itemView.itemPriceTextView

    fun bind(item: ExchangePair) {
        primaryCurrencyView.text = item.primaryCurrency
        secondaryCurrencyView.text = item.secondaryCurrency
        priceView.text = "${item.price}"
    }

}