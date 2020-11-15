package com.mmajka.waluty.Database

import android.app.Application
import androidx.lifecycle.LiveData
import com.mmajka.waluty.Models.CurrencyModelItem
import com.mmajka.waluty.Models.Rate
import com.mmajka.waluty.REST.ApiClient
import com.mmajka.waluty.REST.ApiService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create

class Repository(application: Application) {

        private var dao: Dao
        private val apiClient: ApiClient

    init{
        val database: CurrencyDatabase? = CurrencyDatabase.getInstance(application.applicationContext)
        dao = database!!.currencyDao()

        apiClient = ApiClient

    }

    //-----------------------RETROFIT METODS

    fun getCurrentAsync() = CoroutineScope(Dispatchers.IO).async {
        apiClient.instance.getCurrent()
    }


    //-----------------------LOCAL METODS

    fun getCurrencyLocal(): Deferred<LiveData<MutableList<AllCurrenciesLocal>>> = CoroutineScope(Dispatchers.IO).async {
        dao.getAllLocal()
    }

    fun getFavvorites(): Deferred<LiveData<List<FavoritesLocal>>> = CoroutineScope(Dispatchers.IO).async {
        dao.getFavoritesLocal()
    }

    fun setFavorite(allCurrenciesLocal: AllCurrenciesLocal) = CoroutineScope(Dispatchers.IO).launch {
        dao.setFavorite(allCurrenciesLocal)
    }

    fun unsetFavorite(favoritesLocal: FavoritesLocal) = CoroutineScope(Dispatchers.IO).launch {
        dao.unsetFavorite(favoritesLocal)
    }

    fun inserCurrencies(allCurrenciesLocal: AllCurrenciesLocal) = CoroutineScope(Dispatchers.IO).launch {
        dao.insertItem(allCurrenciesLocal)
    }

}