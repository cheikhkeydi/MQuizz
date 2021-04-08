package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Table.Question;

public class score extends AppCompatActivity {
    private Button button;
    private TextView text_score;

    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        button =(Button) findViewById(R.id.poursuivre);
        text_score = (TextView) findViewById(R.id.text_score);


        text_score.setText("");

        //Recuper le score dans la table questionnaire



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(),liste_score.class);
                startActivity(hello);
                finish();
            }
        });
    }
}