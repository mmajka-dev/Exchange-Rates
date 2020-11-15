package com.mmajka.waluty.Database

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.mmajka.waluty.Fragments.AllCurrencies.AllCurrenciesViewModel
import com.mmajka.waluty.Models.CurrencyModelItem
import com.mmajka.waluty.Models.Rate
import com.mmajka.waluty.REST.ApiClient
import com.mmajka.waluty.REST.LocalData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSource() {


    var updateDate: String = ""
    var ratesData = listOf<Rate>()

    fun updateLocalData(): MutableList<AllCurrenciesLocal>{
        var localData = LocalData.allCurrenciesLocal
        var localItem: AllCurrenciesLocal
        var index = 0
        var name: String?
        var code: String?
        var mid: Float?

        CoroutineScope(Dispatchers.IO).launch {
            ApiClient.instance
                .getResponse()
                .body()!!.forEach {
                    ratesData = it.rates
                    updateDate = it.effectiveDate
                    Log.d("SIZE", "${ratesData.size}")
                }
            do {
                name = ratesData[index].currency
                code = ratesData[index].code
                mid = ratesData[index].mid
                localItem = AllCurrenciesLocal(index, updateDate, code, name, mid)
                localData.add(localItem)
                index += 1
            }while (index < ratesData.size)
            Log.d("SIZE LOCAL DATA", "${localData.size}")
        }

        return localData
    }


}