package com.example.showgallerykotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _imageItems = MutableLiveData<List<ImageItem>>()
    val imageItems: LiveData<List<ImageItem>> = _imageItems

    fun setImageItems(items: List<ImageItem>) {
        _imageItems.value = items
    }
}