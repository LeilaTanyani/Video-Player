package com.silverorange.videoplayer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Video constructor(
    val id: String,
    val title: String,
    val description: String,
    @Json(name = "hlsURL") val url: String,
    val author: Author
) : Serializable

@JsonClass(generateAdapter = true)
data class Author constructor(
    val name: String
)

