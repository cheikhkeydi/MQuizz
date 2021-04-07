package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.ListeScoreAdapter;
import classe.User;

public class liste_score extends AppCompatActivity {

    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_score);

        listView = (ListView) findViewById(R.id.layoutListe_score);
        button =(Button) findViewById(R.id.btn_poursuivre);

        List<User> userList = new ArrayList<>();
        userList.add(new User("Stephanie"));
        userList.add(new User("Cheikh"));




        listView.setAdapter(new ListeScoreAdapter(userList, this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello=new Intent(getApplicationContext(),matiere.class);
                startActivity(hello);
                finish();
            }
        });



    }
}