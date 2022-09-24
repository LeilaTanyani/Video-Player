package com.silverorange.videoplayer.services

import com.silverorange.videoplayer.model.Video
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("videos")
    fun searchLocation(@Query("query") query: String): Call<List<Video>>
}