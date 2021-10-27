package com.example.td2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Repas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repas)

        val annuler = findViewById<Button>(R.id.annuler);

        annuler.setOnClickListener {
            val goToHome =  Intent(this,MainActivity::class.java)
            startActivity(goToHome)
            this.finish()
        }
    }
}