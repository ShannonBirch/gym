package com.shannonbirch.gym.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class AppStartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start_screen);

        // Login and Registration Buttons
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = (findViewById(R.id.registerButton));


        // Below method sets the toolbar as the app bar for the activity.
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        // Brings you to Login Activity when Login buttons is pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppStartScreenActivity.this, LoginActivity.class));
            }
        });

        // Brings you to Register Activity when Register buttons is pressed
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppStartScreenActivity.this, RegisterActivity.class));
            }
        });


    }
}
