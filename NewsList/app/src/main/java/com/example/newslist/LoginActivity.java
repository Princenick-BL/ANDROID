package com.example.newslist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getLocalClassName());

        EditText loginInput = findViewById(R.id.loginInput);
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginInput.getText().toString();
                /* Verifier Si l'utilisateur  bien entré un login*/
                if(login.trim().length() > 0){//Si oui
                    Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
                    intent.putExtra("login",login );
                    startActivity(intent);
                    finish();
                }else { // Si non
                    //Afficher un message d'erreur et demander d'entrer un input
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                    builder1.setMessage("Veuillez entrer un login");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Compris",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            }
        });

    }
}