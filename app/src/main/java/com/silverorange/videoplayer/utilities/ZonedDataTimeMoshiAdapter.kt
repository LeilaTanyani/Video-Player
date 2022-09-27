package com.silverorange.videoplayer.utilities

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class ZonedDataTimeMoshiAdapter {

    @FromJson
    fun fromJson(value: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return format.parse(value)
    }

    @ToJson
    fun toJson(date: Date?): String? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return date?.let { format.format(it) }
    }
}