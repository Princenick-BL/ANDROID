package com.example.td2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonRepas = findViewById<Button>(R.id.buttonRepas);
        val buttonMenu = findViewById<Button>(R.id.buttonMenu);

        buttonRepas.setOnClickListener {
            val goToRepas =  Intent(this,Repas::class.java)
            startActivity(goToRepas)
        }
        buttonMenu.setOnClickListener {
            val goToMenu =  Intent(this,Repas::class.java)
            startActivity(goToMenu)
        }
    }
}