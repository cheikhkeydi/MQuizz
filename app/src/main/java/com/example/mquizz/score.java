package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Table.Question;
import classe.Users;
import data.DbHelper;

public class score extends AppCompatActivity {
    private Button button;
    private TextView text_score,nomUser;
    Users utilisateur;
    List<Users> userList;
    Users currentQ;
    int qid =0;

    String score,nom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        button =(Button) findViewById(R.id.poursuivre);
        text_score = (TextView) findViewById(R.id.text_score);
        nomUser = (TextView) findViewById(R.id.nomUser);


        DbHelper db=new DbHelper(this);
        //Afficher derniere score de la base de donnée
        score=db.getLastScore();
        text_score.setText(score);
        //Afficher derniere nom de la base de donnée
        nom = db.getLastPseudo();
        nomUser.setText(nom);




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