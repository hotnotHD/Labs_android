package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView

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

        lifecycleScope.launch {
            whenResumed {
                while (true) {
                    Log.d(TAG, "Working")
                    delay(1000)
                    textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    companion object {
        val STATE_SECONDS = "curSec"
        const val TAG = "MyWatch"
    }
}
