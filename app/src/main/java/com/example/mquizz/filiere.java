package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.FiliereAdapter;
import classe.Filiere;

public class filiere extends AppCompatActivity {
    private ListView listView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere);

        listView = (ListView) findViewById(R.id.listview_filiere);
        button = (Button) findViewById(R.id.btn_adpaterFiliere);

        List<Filiere> filiereList = new ArrayList<>();

        filiereList.add(new Filiere("Licence 1"));
        filiereList.add(new Filiere("Licence 2"));
        filiereList.add(new Filiere("Licence 3"));
        filiereList.add(new Filiere("Master 1"));

        listView.setAdapter(new FiliereAdapter(this, filiereList));
        




        }
}