package com.example.marvelbunch.models

import com.example.marvelbunch.thumbnail.Thumbnail
import com.squareup.moshi.Json

data class MarvelComicData(
    @Json(name = "results")
     val results: List<Comics>
)

data class Comics (

    @Json(name = "title")
    val title: String,
    @Json(name = "pageCount")
    val pageCount: String,

    @Json(name = "thumbnail")
    val thumbnail: Thumbnail


        )


data class ComicsResponse(@Json(name = "data")
val results : MarvelComicData
)
