package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import classe.User;

public class pseudo extends AppCompatActivity {
    private Button valider;
    private EditText editText;

    public static final String APPLICATION_ID = "B117AFF5-0DC2-9B3E-FF92-AC2671D15700";
    public static final String API_KEY = "C741A5C6-8BED-44B2-AC93-DF7CBA4FC254";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo);
        valider = (Button) findViewById(R.id.valider);
        editText =(EditText) findViewById(R.id.editText);



        //Sauvegarde Pseudo dans BackendLess
        User user = new User("Stephanie");


        Backendless.Data.of(User.class).save(user, new AsyncCallback<User>() {
            @Override
            public void handleResponse(User response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });



        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent hello = new Intent(getApplicationContext(), filiere.class);
                    startActivity(hello);
            }
        });
    }
}