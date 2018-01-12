package com.shannonbirch.gym.gymapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.shannonbirch.gym.gymapp.tools.IsEditTextEmpty.isEditTextEmpty;
import static com.shannonbirch.gym.gymapp.tools.StoreDetails.storeDetails;

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
        //ToDo: Gather more information

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
        }else if (!email1.getText().toString().equals(email2.getText().toString())){
            invalidDetailsString="Email address must match";
        }
        else {



            // Get user defined values
          String email  = email1.getText().toString();
          String pass   = passwordField1.getText().toString();
          String data   = "";


            try {// To create a data variable to send to the server
                data = URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");


                data += "&" + URLEncoder.encode("pass", "UTF-8")
                        + "=" + URLEncoder.encode(pass, "UTF-8");

            }catch (Exception e) {
                //ToDo: More Exception catching bs
            }

            String responseCode = "";
            BufferedReader reader=null;


            try{// To send the data


              URL url = new URL("http://gym.shannonbirch.com/phpScripts/Auth/uRegister.php");

                //Android Stuff
                //ToDo: Figure out how to revert this when no longer needed
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);


                }//End of Android stuff

               // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();//First line is blank
                line = reader.readLine();

                if(line.toString().equals("Success")){

                    String userID = reader.readLine().toString();
                    String token = reader.readLine().toString();
                    Context context = getApplicationContext();

                    storeDetails(userID, token, RegisterActivity.this);

                    //Open home screen
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                    return;


                }else {


                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        Log.e("In read line", line.toString());
                        // Append server response in string
                        sb.append(line + "\n");
                    }


                    responseCode = sb.toString();
                }
              }
              catch(Exception ex)
              {
                ex.printStackTrace();
              }
              finally
              {
                  try{

                      reader.close();
                  }
                  //ToDo: Handle the exception
                  catch(Exception ex) {}
              }


                //ToDo:Figure out where that first \n came from
                //ToDo: Clean
                if(responseCode.equals("\nSuccess\n")) {//User has been registered
                    //Open home screen
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    return;
                }else{//PHP script returns something other than "Success"
                Log.e("ResponseCode:", responseCode);
                invalidDetailsString = "Incorrect system response " + responseCode;
                }

            }




        invalidDetailsToast= Toast.makeText(getApplicationContext(), invalidDetailsString, Toast.LENGTH_LONG);
        invalidDetailsToast.show();

    }//End of register()








}
