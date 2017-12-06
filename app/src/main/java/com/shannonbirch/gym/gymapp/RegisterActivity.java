package com.shannonbirch.gym.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText email1, email2, passwordField1, passwordField2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.tool_bar);


        // Setting the title of the activity in the Toolbar
        TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title) ;
        toolBarTitle.setText(R.string.register);

        email1 = findViewById(R.id.register_email);
        email2 = findViewById(R.id.confirm_register_email);
        passwordField1 = findViewById(R.id.register_password);
        passwordField2 = findViewById(R.id.confirm_register_password);

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
        Toast invalidDetailsToast;
        String invalidDetailsString="";
        if(isEditTextEmpty(email1) ){
            invalidDetailsString="Email is required";

        }else if(isEditTextEmpty(email1)){
            invalidDetailsString="Email is required";
        }
        else if(isEditTextEmpty(passwordField1)){
            invalidDetailsString="Password is required";
        }
        else if(isEditTextEmpty(passwordField2)){
            invalidDetailsString="Password is required";
        }else if (email1 != email2){
            invalidDetailsString="Email address must match";
        }
        else {
            //ToDo: Have this actually connect to a server and check register details
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            return;
        }

        invalidDetailsToast= Toast.makeText(getApplicationContext(), invalidDetailsString, Toast.LENGTH_LONG);
        invalidDetailsToast.show();

    }

    //ToDo: Figure out how to reuse the isEditTextEmpty method across multiple classes properly
    private static boolean isEditTextEmpty(EditText inEditText){
        return inEditText.getText().toString().trim().length()==0;
    }



}
