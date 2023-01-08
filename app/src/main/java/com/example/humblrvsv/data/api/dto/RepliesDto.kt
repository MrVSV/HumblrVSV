package com.example.humblrvsv.data.api.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
//data class RepliesDto(
//    val kind: String,
//    val `data`: DataDto
//) {
//    @JsonClass(generateAdapter = true)
//    data class DataDto(
//        val after: AnyDto?,
//        val dist: AnyDto?,
//        val modhash: AnyDto?,
//        @Json(name = "geo_filter")
//        val geoFilter: String,
//        val children: List<AnyDto>,
//        val before: AnyDto?
//    )
//}