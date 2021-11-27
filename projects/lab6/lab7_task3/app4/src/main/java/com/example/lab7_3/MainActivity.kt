package com.example.lab7_3

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private val photoUrl =
        "https://www.101soundboards.com/storage/board_pictures/22498.jpg?c=1562573153"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            Glide.with(this).load(photoUrl).into(imageView)
        }
    }
}