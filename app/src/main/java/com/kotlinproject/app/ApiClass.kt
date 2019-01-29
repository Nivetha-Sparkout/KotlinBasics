package com.kotlinproject.app

import com.kotlinproject.app.model.CitiesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiClass {

    @GET("get-cities")
    fun getCities(): Call<CitiesResponse>


}