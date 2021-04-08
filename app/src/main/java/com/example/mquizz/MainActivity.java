package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button scripte,acceuil,liste_score,info,jouer,filiere,matiere,score,questionnaire,quizz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scripte=(Button) findViewById(R.id.scripte);
        acceuil=(Button) findViewById(R.id.acceuil);
        liste_score=(Button) findViewById(R.id.liste_score);
        info=(Button) findViewById(R.id.jouer);
        filiere=(Button) findViewById(R.id.filiere);
        matiere=(Button) findViewById(R.id.matiere);
        score=(Button) findViewById(R.id.score);
        questionnaire=(Button) findViewById(R.id.questionnaire);
        jouer=(Button) findViewById(R.id.jouer);
        quizz=(Button) findViewById(R.id.quizz);




        //Redirection des boutons vers les pages correspondantes
        scripte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helloworld1= new Intent(getApplicationContext(), scripte1.class);
                startActivity(helloworld1);
            }
        });
        //QUestionnaire1
        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helloworld1= new Intent(getApplicationContext(), q1.class);
                startActivity(helloworld1);
            }
        });
        //Acceuil
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helloworld1= new Intent(getApplicationContext(), acceuil.class);
                startActivity(helloworld1);
            }
        });
        //Jouer
        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helloworld1= new Intent(getApplicationContext(), pseudo.class);
                startActivity(helloworld1);
            }
        });

        //Filiere
        filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello =new Intent(getApplicationContext(),filiere.class);
                startActivity(hello);
            }
        });

        //info
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(),slider.class);
                startActivity(hello);
            }
        });
        //Matiere
        matiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(),matiere.class);
                startActivity(hello);
            }
        });
        //Score
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(),score.class);
                startActivity(hello);
            }
        });

        //Score
        liste_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(), liste_score.class);
                startActivity(hello);
            }
        });
        //Quizz
        quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(hello);
            }
        });

    }



}