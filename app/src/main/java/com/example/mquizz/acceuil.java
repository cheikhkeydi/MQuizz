package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import adapter.FiliereAdapter;

public class acceuil extends AppCompatActivity {
    private ImageButton commencer,voir_score,aide,continuer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        commencer =(ImageButton) findViewById(R.id.commencer);
        continuer =(ImageButton) findViewById(R.id.continuer);
        voir_score =(ImageButton) findViewById(R.id.voir_score);
        aide =(ImageButton) findViewById(R.id.aide);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



      // Intent i=getIntent();
     //  info.setText(i.getStringExtra(FiliereAdapter.SELECTNIVEAU));

        aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(), slider.class);
                startActivity(hello);
            }
        });

        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello =new Intent(getApplicationContext(),pseudo.class);
                startActivity(hello);
            }
        });

        continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello =new Intent(getApplicationContext(),matiere.class);
                startActivity(hello);
            }
        });

        voir_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello =new Intent(getApplicationContext(),liste_score.class);
                startActivity(hello);
            }
        });

    }
}