package com.example.td3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Roll_Dice extends AppCompatActivity {

    private int NB_DICE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        Intent intent = getIntent();

        if (intent.hasExtra("NB_DICE")) {
            NB_DICE = intent.getIntExtra("NB_DICE",1);
        }

        Button rollButton = (Button) findViewById(R.id.btn);
        TextView resultTextView1 = (TextView) findViewById(R.id.textView1);
        TextView resultTextView2 = (TextView) findViewById(R.id.textView2);
        EditText nbFacesTxt = (EditText) findViewById(R.id.nbFaces);


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp=nbFacesTxt.getText().toString();

                int nbFaces = 6;
                // to avoid NumberFormatException
                if(!"".equals(temp)){
                    nbFaces = Integer.parseInt(temp);
                }
                Dice dice1 = new Dice(nbFaces);
                resultTextView1.setText("" + dice1.roll());

                if(NB_DICE == 2){
                    Dice dice2 = new Dice(nbFaces);
                    resultTextView2.setText(" - " + dice2.roll());

                }



            }
        });
    }
}