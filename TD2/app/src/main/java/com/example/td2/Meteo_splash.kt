package com.example.td2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import java.lang.Thread.sleep


class Meteo_splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meteo_splash)

        val splashScreenStarter: Thread = object : Thread() {
            override fun run() {
                try {
                    var delay = 0
                    while (delay < 1000) {
                        sleep(100)
                        delay = delay + 100
                    }
                    startActivity(Intent(this@Meteo_splash, Meteo::class.java))
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    finish()
                }
            }
        }
        splashScreenStarter.start()
    }
}