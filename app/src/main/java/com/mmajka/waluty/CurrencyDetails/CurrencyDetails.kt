package com.mmajka.waluty.CurrencyDetails

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.mmajka.waluty.R


class CurrencyDetails : AppCompatActivity() {

    //--------------VIEWS
    lateinit var currencyCode: TextView
    lateinit var currencyName: TextView
    lateinit var rate: TextView
    lateinit var button: ExtendedFloatingActionButton
//    lateinit var chart: AnyChartView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_details)

        //-----------INIT
        currencyCode = findViewById(R.id.currencyCode)
        currencyName = findViewById(R.id.currencyName)
        rate = findViewById(R.id.currencyRate)
        button = findViewById(R.id.button)
//        chart = findViewById(R.id.chart)

        Toast.makeText(this, "${intent.getStringExtra("rate")}", Toast.LENGTH_SHORT).show()

        //-----------INTENTS
        if (intent.hasExtra("code")) currencyCode.text = intent.getStringExtra("code")
        if (intent.hasExtra("name")) currencyName.text = intent.getStringExtra("name")
        if (intent.hasExtra("rate")) rate.text = intent.getStringExtra("rate") + "  PLN"

        //-----------CHART

    }
}