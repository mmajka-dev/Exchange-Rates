package com.mmajka.waluty.REST

import androidx.lifecycle.LiveData
import com.mmajka.waluty.Models.CurrencyModelItem
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("A?format=json")
    fun getCurrent(): Call<List<CurrencyModelItem>>

    @GET("A?format=json")
    suspend fun getResponse(): Response<List<CurrencyModelItem>>

}