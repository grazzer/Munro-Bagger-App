package com.graham.munrobagger.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Badge(

    val Name: String,

    @PrimaryKey(autoGenerate = true)
    val number: Int = 0
)
