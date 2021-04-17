package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Table.Matiere;
import adapter.NiveauAdapter;
import adapter.matiereAdapter;
import data.DbHelper;

import static com.example.mquizz.Niveau.NAME;

public class matiere extends AppCompatActivity {
    private ListView list_matiere;
    private TextView textView;
    public final static String FILIERE="FILIERE";
    public List<Matiere> matiereList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere);

        DbHelper db = new DbHelper(this);

        list_matiere = (ListView) findViewById(R.id.list_matiere);
        textView = (TextView) findViewById(R.id.matiere_textview);

        //Recuperation du niveau selectionné
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String annee = preferences.getString(NAME,"Pas trouvé");

        //Recuperation du Filiere selectionner
        Intent i =getIntent();
        String filiere = i.getStringExtra(NiveauAdapter.SELECTFILIERE);
        textView.setText(annee +" - "+filiere);

        //Sauvegarde de filiere
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(FILIERE, filiere);
        editor.commit();

        matiereList = db.getAllMatiere(annee,filiere);



        list_matiere.setAdapter(new matiereAdapter(this,matiereList));



    }
}