package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import adapter.ListeScoreAdapter;
import classe.Users;
import data.DbHelper;

public class liste_score extends AppCompatActivity {

    private ListView listView;
    private ImageButton button,quitter;
    List<Users> quesList;
    Users currentQ;
    int qid =0;
    //private DbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_score);
        listView = (ListView) findViewById(R.id.layoutListe_score);
        button =(ImageButton) findViewById(R.id.btn_poursuivre);
        quitter =(ImageButton) findViewById(R.id.btn_sortir);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DbHelper db = new DbHelper(this);
        quesList = db.getAllUsers();


        listView.setAdapter(new ListeScoreAdapter(quesList, this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello=new Intent(getApplicationContext(),matiere.class);
                startActivity(hello);
                finish();
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello=new Intent(getApplicationContext(),acceuil.class);
                startActivity(hello);
                finish();
            }
        });



    }
}