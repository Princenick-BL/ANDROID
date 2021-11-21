package com.example.newslist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsListApplication app = (NewsListApplication) getApplicationContext();
        String login = app.getLogin();
        setContentView(R.layout.activity_details);
        setTitle(" "+login.toUpperCase(Locale.ROOT).charAt(0)+login.substring(1).toLowerCase(Locale.ROOT)+ " | " + getLocalClassName());
    }
}