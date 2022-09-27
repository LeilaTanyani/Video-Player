package com.silverorange.videoplayer.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

open class BaseViewModel : ViewModel() {

    val error = MutableLiveData<String>()

    val isLoading = MutableLiveData<Boolean>()

    fun <T> makeCall(call: Call<T>, onSuccess: (T?) -> Unit) {
        isLoading.postValue(true)
        error.postValue(null)
        //running a thread
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    error.postValue(response.errorBody().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                error.postValue(e.message.toString())
            }
            isLoading.postValue(false)
        }
    }
}