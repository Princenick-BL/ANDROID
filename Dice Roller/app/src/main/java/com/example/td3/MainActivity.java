package com.example.td3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button unDe = findViewById(R.id.unDe);
        Button deuxDe = findViewById(R.id.deuxDe);

        unDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Roll_Dice.class);
                intent.putExtra("NB_DICE", 1);
                startActivity(intent);
            }
        });
        deuxDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Roll_Dice.class);
                intent.putExtra("NB_DICE", 2);
                startActivity(intent);
            }
        });
    }
   
}