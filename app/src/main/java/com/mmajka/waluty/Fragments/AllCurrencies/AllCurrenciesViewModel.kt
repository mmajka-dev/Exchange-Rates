package com.mmajka.waluty.Fragments.AllCurrencies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mmajka.waluty.Database.AllCurrenciesLocal
import com.mmajka.waluty.Database.CurrencyDatabase
import com.mmajka.waluty.Database.Dao
import com.mmajka.waluty.Database.Repository
import com.mmajka.waluty.Models.CurrencyModelItem
import com.mmajka.waluty.Models.Rate
import com.mmajka.waluty.REST.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllCurrenciesViewModel(application: Application): AndroidViewModel(application) {

    private var repository = Repository(application)
    private var allCurrencies =repository.getCurrencyLocal()
    private var favoriteCurrencies = repository.getFavvorites()
    private var allCurrenciesAsync = repository.getCurrentAsync()

    var localData = mutableListOf<AllCurrenciesLocal>()
    var ratesData = listOf<Rate>()
    var dao: Dao

    init {
        val database = CurrencyDatabase.getInstance(application.applicationContext)
        dao = database!!.currencyDao()
    }

    fun getAllCurrencies(): LiveData<MutableList<AllCurrenciesLocal>> = runBlocking {
            allCurrencies.await()


    }

    fun getAllCurrenciesAsync() = runBlocking {
            allCurrenciesAsync.await()


    }

    fun insertCurrencies(allCurrenciesLocal: AllCurrenciesLocal){
        repository.inserCurrencies(allCurrenciesLocal)
    }


//



}