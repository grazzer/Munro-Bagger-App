package com.graham.munrobagger.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Climbes(

    val munroID: Int,
    val date: Int,
    val weather: String,
    val distance: Int,
    val time: Float,
    val people: String,

    @PrimaryKey(autoGenerate = true)
    val number: Int = 0
)
