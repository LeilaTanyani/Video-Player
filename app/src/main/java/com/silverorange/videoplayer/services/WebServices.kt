package com.silverorange.videoplayer.services

import com.silverorange.videoplayer.model.Video
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("videos")
    fun getVideos(): Call<List<Video>>
}