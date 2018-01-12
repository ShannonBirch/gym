package com.shannonbirch.gym.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static com.shannonbirch.gym.gymapp.tools.CheckToken.checkToken;

public class AppStartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start_screen);

        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.tool_bar);

        if(checkToken(AppStartScreenActivity.this)){//Checks if the user has stored log in details and if they are correct

            //Open home screen
            startActivity(new Intent(AppStartScreenActivity.this, MainActivity.class));

            return;

        }else {//User does not have stored log in details or the token is expired
            // Setting the title of the activity in the Toolbar
            TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title);
            toolBarTitle.setText(R.string.greetings);

            // StoreDetails and Registration Buttons
            Button loginButton = findViewById(R.id.loginButton);
            Button registerButton = (findViewById(R.id.registerButton));

            // Brings you to StoreDetails Activity when StoreDetails buttons is pressed
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





}
