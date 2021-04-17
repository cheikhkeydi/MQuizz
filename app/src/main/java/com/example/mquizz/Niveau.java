package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import adapter.FiliereAdapter;
import adapter.NiveauAdapter;
import Table.Filiere;
import data.DbHelper;

public class Niveau extends AppCompatActivity {
    private List<Filiere> filiereList;
    private NiveauAdapter niveauAdapter;
    private ListView listView;
    private Button button;
    private TextView filiere;
    public  static String NOM;
    public  final static String NAME="NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau);

        listView = (ListView) findViewById(R.id.listview_niveau);
        button = (Button) findViewById(R.id.item_niveau);
        filiere = (TextView) findViewById(R.id.filiere_niveau);

        Intent i =getIntent();
        String name = i.getStringExtra(FiliereAdapter.SELECTNIVEAU);
        filiere.setText(name);

        //Sauvegarde dU niveau selectionner
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, name);
        editor.commit();


        DbHelper db = new DbHelper(this);
        filiereList = db.getAllFilieres();

        listView.setAdapter(new NiveauAdapter(this, filiereList));





    }


}