package com.example.lab6_task2

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.net.URL
import java.util.concurrent.ExecutorService

class ViewModelImage(application: Application) : AndroidViewModel(application) {

    val backgroundThread: ExecutorService = getApplication<MainApplication>().executorService
    val mutableLiveData = MutableLiveData<Bitmap>()

    fun down() {

        backgroundThread.submit {
            val photoUrl =
                "https://www.101soundboards.com/storage/board_pictures/22498.jpg?c=1562573153"
            val newUrl = URL(photoUrl)
            val mIcon = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            mutableLiveData.postValue(mIcon)
            Log.d("image", "$backgroundThread")
        }
        backgroundThread.shutdown()
    }

    fun getBitmapMy(): Bitmap? {
        return mutableLiveData.value
    }
}
