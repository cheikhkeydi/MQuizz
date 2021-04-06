package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.matiereAdapter;
import classe.Matiere_item;
import classe.annee;

public class matiere extends AppCompatActivity {
    private ListView list_matiere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere);

        list_matiere = (ListView) findViewById(R.id.list_matiere);



    //Création des éléments matiere
        List<Matiere_item> matiere_items = new ArrayList<>();

        matiere_items.add(new Matiere_item("Dev- web avancé"));
        matiere_items.add(new Matiere_item("Prog - mobile"));
        matiere_items.add(new Matiere_item("COO"));
        matiere_items.add(new Matiere_item("Dépl - réseau"));
        matiere_items.add(new Matiere_item("Dev- web avancé"));
        matiere_items.add(new Matiere_item("Dev- web avancé"));
        matiere_items.add(new Matiere_item("Dev- web avancé"));
        matiere_items.add(new Matiere_item("Dev- web avancé"));



        list_matiere.setAdapter(new matiereAdapter(this,matiere_items));



    }
}