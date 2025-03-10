package com.mohamed.safenest

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices
{
    @GET("waters")
    fun getAlerts(): Call<List<Alert>>

    @DELETE("waters/{id}")
    fun deleteAlert(@Path("id") id: Int): Call<DeleteModel>
}

