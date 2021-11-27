package com.example.lab7_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL


class ViewModelImage : ViewModel() {
    val mutableLiveData = MutableLiveData<Bitmap>()

    fun down() {
        viewModelScope.launch(Dispatchers.IO) {
            val photoUrl =
                "https://www.101soundboards.com/storage/board_pictures/22498.jpg?c=1562573153"
            val newUrl = URL(photoUrl)
            val mIcon = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            mutableLiveData.postValue(mIcon)
        }
    }

    fun getBitmapMy(): Bitmap? {
        return mutableLiveData.value
    }
}