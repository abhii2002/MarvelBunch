package com.example.marvelbunch.thumbnail

import com.squareup.moshi.Json

data class Thumbnail (
    @Json(name = "path")
    val path: String? = null,
    @Json(name = "extension")
    var extension: String? = null
        )