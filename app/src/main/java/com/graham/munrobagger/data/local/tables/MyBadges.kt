package com.graham.munrobagger.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class MyBadges(

    val dateAchieved : Int,
    val badgeNumber: Int,

    @PrimaryKey(autoGenerate = true)
    val number: Int = 0
)