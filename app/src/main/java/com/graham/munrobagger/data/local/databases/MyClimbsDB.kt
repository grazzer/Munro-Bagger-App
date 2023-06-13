package com.graham.munrobagger.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.graham.munrobagger.data.local.dao.MyClimbsDao
import com.graham.munrobagger.data.local.tables.Badge
import com.graham.munrobagger.data.local.tables.Climbes
import com.graham.munrobagger.data.local.tables.MyBadges

@Database(
    entities = [Badge::class, MyBadges::class, Climbes::class],
    version = 1
)
abstract class MyClimbsDB: RoomDatabase() {
    abstract fun MyClimbsDao(): MyClimbsDao
}