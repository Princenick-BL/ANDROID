package com.example.td3;

import androidx.appcompat.app.AppCompatActivity;

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
        Button rollButton = (Button) findViewById(R.id.btn);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView resultTextView1 = (TextView) findViewById(R.id.textView1);
                TextView resultTextView2 = (TextView) findViewById(R.id.textView2);

                EditText nbFacesTxt = (EditText) findViewById(R.id.nbFaces);
                String temp=nbFacesTxt.getText().toString();

                int nbFaces = 0;
                // to avoid NumberFormatException
                if(!"".equals(temp)){
                    nbFaces = Integer.parseInt(temp);
                }
                Dice dice1 = new Dice(nbFaces);
                Dice dice2 = new Dice(nbFaces);
                resultTextView1.setText("" + dice1.roll());
                resultTextView2.setText("" + dice2.roll());



            }
        });
    }
   
}