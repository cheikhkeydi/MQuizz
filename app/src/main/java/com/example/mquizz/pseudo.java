package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class pseudo extends AppCompatActivity {
    private Button valider;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo);
        valider = (Button) findViewById(R.id.valider);
        editText =(EditText) findViewById(R.id.editText);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent hello = new Intent(getApplicationContext(), filiere.class);
                    startActivity(hello);
            }
        });
    }
}