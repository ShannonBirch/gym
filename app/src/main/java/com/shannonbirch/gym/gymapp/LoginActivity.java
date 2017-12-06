package com.shannonbirch.gym.gymapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.tool_bar);

        emailField = (EditText) findViewById(R.id.login_email);
        passwordField = (EditText) findViewById(R.id.login_password);

        // Setting the title of the activity in the Toolbar
        TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title) ;
        toolBarTitle.setText(R.string.login);

        Button loginButton = findViewById(R.id.loginButton);

        // Sets onClick for the loginButton
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               login();

            }
        });
    }


    private void login(){
        Toast invalidDetailsToast;
        String invalidDetailsString="";
        if(isEditTextEmpty(emailField)){
            invalidDetailsString="Email is required";

        }else if(isEditTextEmpty(passwordField)){
            invalidDetailsString="Password is required";

        }else {

            //ToDo: Have this actually connect to a server and check login details
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            return;
        }

        invalidDetailsToast= Toast.makeText(getApplicationContext(), invalidDetailsString, Toast.LENGTH_LONG);
        invalidDetailsToast.show();
    }

    public static boolean isEditTextEmpty(EditText inEditText){
        return inEditText.getText().toString().trim().length()==0;
    }




}
