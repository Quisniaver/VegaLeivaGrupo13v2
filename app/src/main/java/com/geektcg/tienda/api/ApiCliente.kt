
package com.geektcg.tienda.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // IMPORTANTE: 10.0.2.2 = localhost de tu PC cuando usas un emulador Android.
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}