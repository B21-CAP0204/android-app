package com.juarai.capstone.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("api2")
    suspend fun getData()

    @GET("api2")
    suspend fun getDataKK(@Query("no_kk") no_kk: Int)
}