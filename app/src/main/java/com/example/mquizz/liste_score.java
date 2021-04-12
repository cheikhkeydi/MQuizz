package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import adapter.ListeScoreAdapter;
import classe.Users;
import data.DbHelper;

public class liste_score extends AppCompatActivity {

    private ListView listView;
    private Button button;
    List<Users> quesList;
    Users currentQ;
    int qid =0;
    //private DbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_score);
        listView = (ListView) findViewById(R.id.layoutListe_score);
        button =(Button) findViewById(R.id.btn_poursuivre);

        DbHelper db = new DbHelper(this);
        //quesList=db.getListUsers();
        //currentQ=quesList.get(qid);
        /*quesList = new ArrayList<>();
        quesList.add(new Users("Malick"));
        quesList.add(new Users("Fallou"));  */
        quesList = db.getAllUsers();



        //List<Users> userList = new ArrayList<>();
       // userList.add(new Users("Makhtar"));

        listView.setAdapter(new ListeScoreAdapter(quesList, this));

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