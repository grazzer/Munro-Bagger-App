package com.graham.munrobagger.repository

import com.graham.munrobagger.data.remote.MunroApi
import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.data.remote.responses.MunroList
import com.graham.munrobagger.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MunroRepository @Inject constructor
    (private val api: MunroApi)
{

    suspend fun getMunroList(): Resource<MunroList>{
        val response = try {
            api.getMunrosList()
        } catch (e: Exception) {
            return Resource.Error("An unknown error has occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getMunro(munroName: String): Resource<Munro>{
        val response = try {
            api.getMunroByName(munroName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error has occured.")
        }
        return Resource.Success(response)
    }
}