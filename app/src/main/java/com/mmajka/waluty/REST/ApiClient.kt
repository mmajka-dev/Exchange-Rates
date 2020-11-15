package com.mmajka.waluty.REST

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    val client = OkHttpClient.Builder().build()
    val baseURL = "http://api.nbp.pl/api/exchangerates/tables/"
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val instance by lazy {
       val retrofit =  Retrofit.Builder()
            .client(client)
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    retrofit.create(ApiService::class.java)
    }

}