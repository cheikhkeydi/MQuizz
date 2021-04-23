package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Table.Question;
import adapter.NiveauAdapter;
import classe.Users;
import data.DbHelper;

import static com.example.mquizz.Niveau.NAME;
import static com.example.mquizz.q1.LASTSCORE;


public class score extends AppCompatActivity {
    private ImageButton button,recommencer;
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

        button =(ImageButton) findViewById(R.id.poursuivre);
        recommencer =(ImageButton) findViewById(R.id.recommencer);

        text_score = (TextView) findViewById(R.id.text_score);
        nomUser = (TextView) findViewById(R.id.nomUser);


        DbHelper db=new DbHelper(this);
        //Recuperation de la derniere score  derniere score de la base de donnée
        //Intent i =getIntent();
        //String scores = i.getStringExtra(q1.LASTSCORE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String scores = preferences.getString(LASTSCORE,"Pas trouvé");

        db.updateData(Integer.parseInt(scores));
        score=db.getLastScore();
        text_score.setText(scores+"/20");
        //Afficher derniere nom de la base de donnée
        nom = db.getLastPseudo();
        nomUser.setText(nom);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(),liste_score.class);
                startActivity(hello);
            }
        });

        recommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(),q1.class);
                startActivity(hello);
            }
        });
    }
}