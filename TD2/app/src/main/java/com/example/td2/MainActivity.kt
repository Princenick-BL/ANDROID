package com.example.td2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonMeteo = findViewById<Button>(R.id.buttonMeteo);
        val buttonRepas = findViewById<Button>(R.id.buttonRepas);

        buttonMeteo.setOnClickListener {
            startActivity(  Intent(this,Meteo_splash::class.java))
        }
        buttonRepas.setOnClickListener {
            startActivity(Intent(this,Repas::class.java))
        }


    }
};