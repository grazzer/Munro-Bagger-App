package com.graham.munrobagger.data.remote

import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.data.remote.responses.MunroList
import retrofit2.http.GET
import retrofit2.http.Path

interface MunroApi {

    @GET("munros")
    suspend fun getMunrosList(): MunroList

    @GET("munros/name{name}")
    suspend fun getMunro(
        @Path("name") name: String
    ): Munro

}