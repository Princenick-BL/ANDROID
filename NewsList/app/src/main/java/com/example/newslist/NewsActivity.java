package com.example.newslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class NewsActivity extends AppCompatActivity {

    Button logoutBtn;
    TextView detailsBtn;
    String login = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();

        logoutBtn = findViewById(R.id.logout);
        detailsBtn = findViewById(R.id.details);

        if (intent.hasExtra("login")) {
            login = intent.getStringExtra("login");
        }

        setTitle(" "+login.toUpperCase(Locale.ROOT).charAt(0)+login.substring(1).toLowerCase(Locale.ROOT)+ " | " + getLocalClassName());

        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this, DetailsActivity.class);
                intent.putExtra("login",login );
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}