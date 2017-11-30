package com.shannonbirch.gym.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.tool_bar);

        // Setting the title of the activity in the Toolbar
        TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title) ;
        toolBarTitle.setText(R.string.register);

        Button loginButton = findViewById(R.id.loginButton);

    }
}
