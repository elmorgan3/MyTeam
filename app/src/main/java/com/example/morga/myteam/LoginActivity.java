package com.example.morga.myteam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnAcceder = (Button)findViewById(R.id.buttonAcceder);
    }

    public void Acceder(View view) {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
    }
}
