package com.example.marvelbunch.models

import com.example.marvelbunch.thumbnail.Thumbnail
import com.squareup.moshi.Json

data class EventsModel (
    @Json(name = "results")
    val results: List<Events>
    )



data class eventsComicsAvailable(
    @Json(name = "available")
    val available: Int
)

data class eventsCharactersAvailable(
    @Json(name = "available")
    val available: Int
)

data class eventsStoriesAvailable(
    @Json(name = "available")
    val available: Int
)

data class eventsCreatorsAvailable(
    @Json(name = "available")
    val available: Int
)




data class Events (

    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,

    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,


    @Json(name = "comics")
    val comics : eventsComicsAvailable,

    @Json(name = "characters")
    val characters : eventsCharactersAvailable,

    @Json(name = "stories")
    val stories : eventsStoriesAvailable,

    @Json(name = "creators")
    val creators : eventsCreatorsAvailable,


)


data class EventsResponse(@Json(name = "data")
                          val results : EventsModel
)
