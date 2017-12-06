package com.shannonbirch.gym.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private String email, email2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.tool_bar);

        // Setting the title of the activity in the Toolbar
        TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title) ;
        toolBarTitle.setText(R.string.register);

        Button registerButton = findViewById(R.id.loginButton);
        // Sets onClick for the registerButton
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();

            }
        });


    }


    private void register(){
        //ToDo: Figure out how to reuse the isEditTextEmpty method across multiple classes properly

    }


}
