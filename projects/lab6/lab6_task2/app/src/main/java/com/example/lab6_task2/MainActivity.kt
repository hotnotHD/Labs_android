package com.example.lab6_task2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private val myData: ViewModelImage by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            if (myData.getBitmapMy() == null) {
                myData.down()
                Log.d(TAG, "image downloaded")
            }
        }

        myData.mutableLiveData.observe(this) {
            imageView.setImageBitmap(myData.getBitmapMy())
            Log.d(TAG, "set image")
        }
    }

    companion object {
        const val TAG = "MyIm"
    }
}