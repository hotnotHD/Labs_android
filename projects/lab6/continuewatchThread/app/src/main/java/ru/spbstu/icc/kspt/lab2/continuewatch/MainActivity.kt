package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var backgroundThread: Thread


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt(STATE_SECONDS, secondsElapsed) }
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                secondsElapsed = getInt(STATE_SECONDS)
            }
        }
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)


    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        backgroundThread = Thread {
            while (!backgroundThread.isInterrupted) {
                Log.d(TAG, "$backgroundThread working")
                try {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                    }
                } catch (e: InterruptedException){
                    Log.d(TAG, "exception")
                    backgroundThread.interrupt()
                }
            }
        }
        backgroundThread.start()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        backgroundThread.interrupt()
    }

    companion object {
        val STATE_SECONDS = "curSec"
        const val TAG = "MyWatch"
    }
}
