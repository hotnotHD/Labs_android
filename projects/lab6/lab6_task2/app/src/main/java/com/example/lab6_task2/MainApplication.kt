package com.example.lab6_task2

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainApplication : Application() {
    val executorService: ExecutorService = Executors.newFixedThreadPool(1)
}