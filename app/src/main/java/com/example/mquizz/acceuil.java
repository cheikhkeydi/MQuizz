package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class acceuil extends AppCompatActivity {
    private Button valider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        valider =(Button) findViewById(R.id.commencer);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello =new Intent(getApplicationContext(),pseudo.class);
                startActivity(hello);
            }
        });
    }
}