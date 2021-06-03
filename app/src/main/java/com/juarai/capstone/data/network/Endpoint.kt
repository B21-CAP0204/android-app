package com.juarai.capstone.data.network

import retrofit2.http.GET

interface Endpoint {
    @GET("api2/")
    suspend fun getData()
}