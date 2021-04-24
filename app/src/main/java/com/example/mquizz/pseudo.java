package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import classe.Users;
import data.DbHelper;

import static android.widget.Toast.*;

public class pseudo extends AppCompatActivity {
    private Button valider;
    private EditText editText;
    private String name;
    Users utilisateur;

    DbHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo);
        valider = (Button) findViewById(R.id.valider);
        editText =(EditText) findViewById(R.id.editText);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       // List<Users> user = new ArrayList<>();

       DbHelper myDB =new DbHelper(this);


            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String entry = editText.getText().toString();
                    if(entry.isEmpty())
                    {
                        editText.setHintTextColor(Color.RED);
                        Toast toast = Toast.makeText(getBaseContext(), "Veuillez entrer votre pseudo!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        myDB.AjouterUSer(entry);
                        myDB.updateData(18);
                        Intent hello = new Intent(getApplicationContext(), filiere.class);
                        startActivity(hello);
                        Toast.makeText(getBaseContext(), "Bienvenue "+entry, LENGTH_SHORT).show();
                    }

                }
            });

    }



}