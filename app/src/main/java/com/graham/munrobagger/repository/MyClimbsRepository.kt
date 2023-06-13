package com.graham.munrobagger.repository

import com.graham.munrobagger.data.local.dao.MyClimbsDao
import com.graham.munrobagger.data.local.tables.Climbes
import com.graham.munrobagger.data.remote.MunroApi
import com.graham.munrobagger.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MyClimbsRepository @Inject constructor
    (private val dao: MyClimbsDao) {

        suspend fun addClimb(climb: Climbes) {
            dao.insertClimb(climb)
        }
}