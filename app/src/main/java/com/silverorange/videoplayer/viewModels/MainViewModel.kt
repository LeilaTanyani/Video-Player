package com.silverorange.videoplayer.viewModels

import androidx.lifecycle.MutableLiveData
import com.silverorange.videoplayer.model.Video
import com.silverorange.videoplayer.services.WebServices


class MainViewModel(private val webService: WebServices) :
    BaseViewModel() {

    val videos = MutableLiveData<List<Video>>()

    init {
        getVideos()

    }

    private fun getVideos() {
        makeCall(webService.getVideos()) {
            videos.postValue(it)
        }
    }


}