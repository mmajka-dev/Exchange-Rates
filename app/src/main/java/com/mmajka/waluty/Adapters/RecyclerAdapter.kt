package com.mmajka.waluty.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mmajka.waluty.Database.AllCurrenciesLocal
import com.mmajka.waluty.Models.CurrencyModelItem
import com.mmajka.waluty.Models.Rate
import com.mmajka.waluty.R

class RecyclerAdapter(private val currencyList: MutableList<Rate>, val itemCLickListener: onClickInterface): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val currency = inflater.inflate(R.layout.currency_card, parent, false)

        return ViewHolder(currency)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate =currencyList.get(position)
        holder.bind(rate, itemCLickListener)

//        var currencyCode = holder.currencyID
//        var currencyName = holder.currencyName
//        var currencyRate = holder.currencyRate
//
//        currencyCode.setText(currencyList[position].code)
//        currencyName.setText(currencyList[position].currency)
//        currencyRate.setText(currencyList[position].mid)
//
//        Log.d("CHECK", "${currencyList.size}")



    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val currencyID = view.findViewById(R.id.currencyID) as TextView
    val currencyName = view.findViewById(R.id.currencyCode) as TextView
    val currencyRate = view.findViewById(R.id.currencyRate) as TextView

    fun bind(rate: Rate, OnClickInterface: onClickInterface){
        currencyID.text = rate.code
        currencyName.text = rate.currency
        currencyRate.text = rate.mid.toString()

        itemView.setOnClickListener {
            OnClickInterface.onClickListener(rate, adapterPosition, itemView)
        }

        itemView.setOnLongClickListener {
            OnClickInterface.onLongClickListener(rate, adapterPosition, itemView)
            return@setOnLongClickListener true
        }
    }
}

interface onClickInterface{
    fun onClickListener(rate: Rate, position: Int, view: View)
    fun onLongClickListener(rate: Rate, position: Int, view: View)
}

