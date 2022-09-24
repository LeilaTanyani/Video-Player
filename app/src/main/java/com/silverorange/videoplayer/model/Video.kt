package com.silverorange.videoplayer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Video constructor(
    @Json(name = "id") val id: Long,
    //todo TBC
) : Serializable

