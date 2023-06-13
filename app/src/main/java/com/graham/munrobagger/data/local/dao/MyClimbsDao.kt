package com.graham.munrobagger.data.local.dao

import androidx.room.*
import com.graham.munrobagger.data.local.tables.Badge
import com.graham.munrobagger.data.local.tables.Climbes
import com.graham.munrobagger.data.local.tables.MyBadges
import kotlinx.coroutines.flow.Flow

@Dao
interface MyClimbsDao {

    @Insert
    suspend fun insertBadge(badge: Badge)

    @Insert
    suspend fun insertClimb(climb: Climbes)

    @Insert
    suspend fun insertMyBadge(myBadge: MyBadges)


}

//    @Insert
//    suspend fun insertStock(stock: Stock)
//
//    @Update
//    suspend fun updateStock(stock: Stock)
//
//    @Delete
//    suspend fun removeStock(stock: Stock)
//
//    @Query("SELECT * FROM Stock WHERE id = :id")
//    suspend fun getStockById(id: Int): Stock
//
//    @Query("SELECT * FROM Stock WHERE locationName = :location")
//    fun getStockInLocation(location: String): Flow<List<Stock>>