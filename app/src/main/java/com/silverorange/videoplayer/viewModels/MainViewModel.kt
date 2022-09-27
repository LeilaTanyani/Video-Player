package com.silverorange.videoplayer.viewModels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.silverorange.videoplayer.model.Video
import com.silverorange.videoplayer.services.WebServices


class MainViewModel(private val webService: WebServices) :
    BaseViewModel() {

     val videos = MutableLiveData<List<Video>>()
    val currentVideo = MediatorLiveData<Video>().apply {
        addSource(videos) { videos ->
            if (videos.isNotEmpty()) {
                postValue(videos[0])
            }
        }
    }

    init {
        getVideos()

    }

    private fun getVideos() {
        makeCall(webService.getVideos()) {
            it?.sortedByDescending { video -> video.publishedAt }
            videos.postValue(it)
        }
    }


}