package com.example.lab7_task2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ViewModelImage : ViewModel() {

    private lateinit var backgroundThread: ExecutorService
    val mutableLiveData = MutableLiveData<Bitmap>()

    fun down() {
        backgroundThread = Executors.newFixedThreadPool(1)
        backgroundThread.execute {
            val photoUrl =
                "https://www.101soundboards.com/storage/board_pictures/22498.jpg?c=1562573153"
            val newUrl = URL(photoUrl)
            val mIcon = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            mutableLiveData.postValue(mIcon)
        }
        backgroundThread.shutdown()
    }

    fun getBitmapMy(): Bitmap? {
        return mutableLiveData.value
    }
}
