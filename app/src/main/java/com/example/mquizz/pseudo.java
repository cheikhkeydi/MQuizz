package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import classe.User;

import static android.widget.Toast.*;

public class pseudo extends AppCompatActivity {
    private Button valider;
    private EditText editText;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo);
        valider = (Button) findViewById(R.id.valider);
        editText =(EditText) findViewById(R.id.editText);

        List<User> user = new ArrayList<>();








            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(editText.getText().toString().isEmpty())
                    {
                        editText.setHintTextColor(Color.RED);
                        Toast toast = Toast.makeText(getBaseContext(), "Veuillez entrer votre pseudo!!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                    else{
                        user.add(new User(editText.getText().toString()));
                        Intent hello = new Intent(getApplicationContext(), filiere.class);
                        startActivity(hello);
                        Toast.makeText(getBaseContext(), "Bienvenue "+editText.getText().toString(), LENGTH_SHORT).show();
                    }

                }
            });

    }
}