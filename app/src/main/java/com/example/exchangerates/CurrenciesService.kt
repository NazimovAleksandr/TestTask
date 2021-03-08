package com.example.exchangerates

import com.example.exchangerates.json.Currencies
import retrofit2.Call
import retrofit2.http.GET

interface CurrenciesService {
    @GET("/daily_json.js")
    fun getCurrencies(): Call<Currencies>
}