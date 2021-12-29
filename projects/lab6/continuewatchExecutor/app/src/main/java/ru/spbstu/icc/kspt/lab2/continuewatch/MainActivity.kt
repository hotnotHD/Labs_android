package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var future: Future<*>


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
        future.cancel(true)
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        val backgroundThread = (applicationContext as MainApplication).executorService
        future = backgroundThread.submit {
            while (!backgroundThread.isShutdown) {
                Log.d(TAG, "$backgroundThread working")
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                }

            }
        }
        super.onResume()
    }

    companion object {
        val STATE_SECONDS = "curSec"
        const val TAG = "MyWatch"
    }
}
