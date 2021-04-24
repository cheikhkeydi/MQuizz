package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Table.Niveau;
import adapter.FiliereAdapter;
import classe.Filiere;
import data.DbHelper;

public class filiere extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private List<Niveau> niveauList;
    private Niveau niveau;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere);


        listView = (ListView) findViewById(R.id.listview_filiere);
        button = (Button) findViewById(R.id.btn_adpaterFiliere);
        textView = (TextView) findViewById(R.id.filiere_textview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DbHelper db = new DbHelper(this);
        niveauList = db.getAllNiveau();

        listView.setAdapter(new FiliereAdapter(this, niveauList));

        }
}