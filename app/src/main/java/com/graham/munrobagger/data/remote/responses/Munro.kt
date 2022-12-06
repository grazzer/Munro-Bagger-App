package com.graham.munrobagger.data.remote.responses

data class Munro(
    val gridref_eastings: String,
    val gridref_letters: String,
    val gridref_northings: String,
    val height: Int,
    val latlng_lat: Double,
    val latlng_lng: Double,
    val meaning: String,
    val metoffice_loc_id: String,
    val name: String,
    val region: String,
    val smcid: String
)