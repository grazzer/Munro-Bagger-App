package com.graham.munrobagger.data.remote

import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.data.remote.responses.MunroList
import retrofit2.http.GET
import retrofit2.http.Path

interface MunroApi {

    @GET("munros")
    suspend fun getMunrosList(): MunroList

    @GET("hills/{name}")
    suspend fun getMunroByName(
        @Path("name") name: String
    ): Munro

    @GET("hills/{number}")
    suspend fun getMunroByNumber(
        @Path("number") name: String
    ): Munro

}