package com.example.marvelbunch.models

import com.example.marvelbunch.thumbnail.Thumbnail
import com.squareup.moshi.Json

data  class CharacterModel(
    @Json(name = "results")
    val results: List<Characters>
)

data class Characters(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val title: String,
    @Json(name = "description")
    val description : String,

    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,

    @Json(name = "comics")
    val comics : comicsAvailable,

    @Json(name = "series")
    val series : seriesAvailable,

    @Json(name = "stories")
    val stories : storiesAvailable,

    @Json(name = "events")
    val events : eventsAvailable,


    )

data class comicsAvailable(
    @Json(name = "available")
    val available: Int
)

data class seriesAvailable(
    @Json(name = "available")
    val available: Int
)

data class storiesAvailable(
    @Json(name = "available")
    val available: Int
)

data class eventsAvailable(
    @Json(name = "available")
    val available: Int
)

data class CharacterResponse(@Json(name = "data")
                             val results : CharacterModel
)

