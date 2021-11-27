package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var backgroundThread: ExecutorService


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt(STATE_SECONDS, secondsElapsed) }
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                secondsElapsed = getInt(STATE_SECONDS)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        backgroundThread.shutdown()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        backgroundThread = Executors.newFixedThreadPool(1)
        backgroundThread.execute {
            while (!backgroundThread.isShutdown) {
                Log.d(TAG, "$backgroundThread working")
                try {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                    }
                } catch (e: Exception){
                    Log.d(TAG, "error: $e")
                }
            }
        }
    }

    companion object {
        val STATE_SECONDS = "curSec"
        const val TAG = "MyWatch"
    }
}
